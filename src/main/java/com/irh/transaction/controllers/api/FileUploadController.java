package com.irh.transaction.controllers.api;

import com.irh.transaction.WebHelper;
import com.irh.transaction.controllers.BaseController;
import com.irh.transaction.exceptions.web.ApiException;
import com.irh.transaction.exceptions.web.ApiResponse;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

/**
 * The API controller to manage file upload.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/api/file-upload")
public class FileUploadController extends BaseController{

    private static final String[] VALID_MODELS = new String[]{"product"};

    /**
     * Saves the uploaded file.
     *
     * @param model the model the file is for.
     * @param file  the uploaded file.
     * @return the response containing the URL path of the saved file.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ApiResponse save(@RequestParam("model") String model,
                            @RequestParam("file") CommonsMultipartFile file,
                            HttpServletRequest request) throws ApiException{
        try{
            if(file == null || file.getSize() == 0){
                throw new ApiException(ApiResponse.INVALID_ENTITY_CODE,
                        "The file must be provided.");
            }
            if(model == null || model.trim().length() == 0){
                throw new ApiException(ApiResponse.INVALID_ENTITY_CODE,
                        "The model cannot be null or empty.");
            }
            boolean modelValid = false;
            for(String validModel : VALID_MODELS){
                if(validModel.equals(model)){
                    modelValid = true;
                    break;
                }
            }

            if(!modelValid){
                throw new ApiException(ApiResponse.INVALID_ENTITY_CODE,
                        "The model is not allowed.");
            }


            String urlPath = getAccount().getHqId() + "/" + UUID.randomUUID().toString() + "."
                    + FilenameUtils.getExtension(file.getOriginalFilename());

            String fileLocation = "/data/static/img.51hchc.com/" + model + "/";

            String imgUrlHead = "http://img.51hchc.com/" + model + "/";

//            String fileName = UUID.randomUUID().toString() + "."
//                    + FilenameUtils.getExtension(file.getOriginalFilename());

            String url = imgUrlHead + urlPath;
            file.transferTo(new File(fileLocation + urlPath));

            return new ApiResponse(url);
        }catch(ApiException ex){
            throw WebHelper.logException(getLogger(), FileUploadController.class.getName()
                    + "#save(String, MultipartFile, HttpServletRequest)", ex);
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), FileUploadController.class.getName()
                            + "#save(String, MultipartFile, HttpServletRequest)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }
}
