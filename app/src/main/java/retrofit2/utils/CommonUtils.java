package retrofit2.utils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;


import com.jackhan.wgleadlife.utils.FileUtils;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author hanhuizhong
 * @date 2016-7-7
 */
public class CommonUtils {

    /**
     * 上传文件
     *
     * @param activity
     * @param params
     * @param files
     * @author hanhuizhong
     * @date 2016-7-7
     */
    public static Map<String, RequestBody> updateFilesParams(Activity activity,
                                                             Map<String, String> params, List<File> files) {
        // TODO Auto-generated method stub
        Map<String, RequestBody> fileParams = new HashMap<String, RequestBody>();
        for (File file : files) {

            RequestBody fileBody;
            try {
                fileBody = RequestBody.create(
                        MediaType.parse(FileUtils.getMimeType(file.toURI().toURL())),
                        file);
                fileParams.put("file\"; filename=\"" + file.getName() + "",
                        fileBody);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        for (Map.Entry<String, String> entry : params.entrySet()) {

            fileParams.put(
                    entry.getKey(),
                    RequestBody.create(MediaType.parse("text/plain"),
                            entry.getValue()));

        }
        return fileParams;
    }
}
