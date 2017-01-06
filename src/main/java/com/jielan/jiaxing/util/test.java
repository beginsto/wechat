package com.jielan.jiaxing.util;

import com.jielan.jiaxing.util.hzdb.LocalJDBCTemplate;

import java.util.List;

/**
 * Created by Administrator on 2016/11/18.
 */
public class test {
    /*public static List<Object[]> getList() throws Exception {
        return LocalJDBCTemplate.queryForList("select * from fetionanswer_userinfo where imonth = 10 order by openid desc",new Object[]{});
    }

    public static List<Object[]> getdetail(String openid) throws Exception {
        return LocalJDBCTemplate.queryForList("select * from fetionanswer_detail WHERE DATE_FORMAT(answertime, '%Y-%m-%d') >= '2016-10-20' and openid = ? ",new Object[]{openid});
    }

    public static void main(String[] args) throws Exception {
        int[] arr = {0,0,0,0,0,0};
        List<Object[]> list = getList();
        for(int i = 0; i < list.size(); i++){
            List<Object[]> detail = getdetail(String.valueOf(list.get(i)[1]));
            for(int j = 0; j < detail.size(); j++){
                if (String.valueOf(detail.get(j)[3]).equals("综合知识")){
                    arr[0] += 1;
                    if(String.valueOf(detail.get(j)[4]).equals(String.valueOf(detail.get(j)[5]))){
                        arr[1] +=1;
                    }
                }else if(String.valueOf(detail.get(j)[3]).equals("营业知识")){
                    arr[2] += 1;
                    if(String.valueOf(detail.get(j)[4]).equals(String.valueOf(detail.get(j)[5]))){
                        arr[3] +=1;
                    }
                }else if(String.valueOf(detail.get(j)[3]).equals("政企知识")){
                    arr[4] += 1;
                    if(String.valueOf(detail.get(j)[4]).equals(String.valueOf(detail.get(j)[5]))){
                        arr[5] +=1;
                    }
                }
            }

            System.out.println(i+" "+String.valueOf(list.get(i)[1])+" "+arr[0]+" "+arr[1]+" "+arr[2]+" "+arr[3]+" "+arr[4]+" "+ arr[5]);
            LocalJDBCTemplate.updateData("update fetionanswer_userinfo set zonghe = ? ,zongheright = ? ,yingye = ? ,yingyeright = ?, zhengqi = ?, zhengqiright = ? where openid = ? and imonth = 10",
                    new Object[]{arr[0],arr[1],arr[2],arr[3],arr[4],arr[5],String.valueOf(list.get(i)[1])});
            arr[0] = 0;
            arr[1] = 0;
            arr[2] = 0;
            arr[3] = 0;
            arr[4] = 0;
            arr[5] = 0;
        }
    }*/

    public static List<Object[]> getList() throws Exception {
        return LocalJDBCTemplate.queryForList("SELECT pid,openid,award,raffle_type,raffle_time,phone from test1 order by phone asc",new Object[]{});
    }

    public static int getdetail(String phone ,String award,String raffle_type) throws Exception {
        return LocalJDBCTemplate.queryForInt("select count(*) from test where phone  = ? and award = ? ",new Object[]{phone,award});
    }

    public static void main(String[] args) throws Exception {
        List<Object[]> list = getList();
        for (int i = 0; i < list.size(); i++){
          //  System.out.println(list.get(i)[0].toString()+"-"+list.get(i)[1].toString()+"-"+list.get(i)[2].toString()+"-"+list.get(i)[3]+"-"+list.get(i)[4]+"-"+list.get(i)[5]+"-"+list.get(i)[6]);
            if(String.valueOf(list.get(i)[5]).equals("null")){
                System.out.println(list.get(i)[1]+" "+list.get(i)[2]+" "+list.get(i)[3]+" "+list.get(i)[4]);
            }else{
                String phone = String.valueOf(list.get(i)[5]);
                String award = String.valueOf(list.get(i)[2]);
                String raffle_type = String.valueOf(list.get(i)[3]);
                int temp = getdetail(phone,award,raffle_type);
                if( temp == 0){
                        System.out.println(list.get(i)[1]+" "+list.get(i)[2]+" "+list.get(i)[3]+" "+list.get(i)[4]);
                }
            }

        }
    }
}
