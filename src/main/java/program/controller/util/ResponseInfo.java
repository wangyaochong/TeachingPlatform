package program.controller.util;

/**
 * Created by【王耀冲】on 【2016/12/15】 at 【3:02】.
 */
public class ResponseInfo {

    public final static String STATUS_OK="ok";
    public final static String STATUS_BAD="bad";
    String statusFlag="";
    String message="";
    Object data=null;

    public ResponseInfo(String statusFlag, String message, String data){
        this.statusFlag = statusFlag;
        this.message=message;
        this.data=data;
    }
    public ResponseInfo(String statusFlag, String message){
        this.statusFlag = statusFlag;
        this.message=message;
    }
    public ResponseInfo(String statusFlag){
        this.statusFlag = statusFlag;
    }
    public String getStatusFlag() {
        return statusFlag;
    }
    public void setStatusFlag(String statusFlag) {
        this.statusFlag = statusFlag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
