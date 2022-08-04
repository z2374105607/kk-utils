package net.kkppyy.utils.uuid;

import java.util.UUID;

/**
 * Created by Administrator on 2017/7/17.
 */
public class UUIDUtil {
    public static String getUUID() {
        String id= UUID.randomUUID().toString();//生成的id942cd30b-16c8-449e-8dc5-028f38495bb5中间含有横杠
        id=id.replace("-", "");//替换掉中间的那个斜杠
        return id;
    };


}
