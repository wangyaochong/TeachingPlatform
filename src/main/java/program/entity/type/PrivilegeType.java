package program.entity.type;

/**
 * Created by【王耀冲】on 【2016/12/29】 at 【22:30】.
 */
public class PrivilegeType {
    //由于一个用户可能只有一种权限，
    // 那么如果权限entity拥有那么多字段是一种浪费，
    // 所以只留下type字段+group字段
    //这里不使用枚举类型，是因为每次调用时都需要使用toString方法，感觉很麻烦
    public static final String FRONT_MESSAGE="FRONT_MESSAGE";
    public static final String DOCUMENT="DOCUMENT";
    public static final String VIDEO="VIDEO";
    public static final String ASSIGNMENT="ASSIGNMENT";
    public static final String USER_MANAGEMENT="USER_MANAGEMENT";
    public static final String GROUP_EDIT="GROUP_EDIT";
    public static final String SUPER="SUPER";
}
