package day02;

import java.util.Scanner;

/**
 * @ClassName CrossBridge
 * @Description 先由10陪1过，10再返回原地花费2分钟，10再陪2过，10再返回原地花费4分钟，10最终和5一起过花费10分钟。总花费16分钟
 * 1和2一起过去，1再返回花费3分钟，5和10一起过去花费10分钟，2返回花费2分钟，1和2一起过去花费2分钟，共计17分钟。
 * @Auther danni
 * @Date 2019/7/31 18:40]
 * @Version 1.0
 **/

public class CrossBridge {
    public int[] Init(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入过桥人数：");
        int num=scanner.nextInt();
        int[] person=new int[num];
        System.out.println("请输入每人过桥所需的时间：");
        for (int i = 0; i <num ; i++) {
            person[i]=scanner.nextInt();
        }

        for (int i = 1; i <num ; i++) {
            if(person[i]<person[i-1]){
                int min=person[i];
                int j=i;
                while(person[j]>=min&&j>0){
                    person[j]=person[j-1];
                    j--;
                }
                person[j]=min;
            }
        }
        return person;
    }
    public int solution(int[] n){
        int time=0;
        boolean[] pass=new boolean[n.length];
        for (int i = 0; i <=n.length-2 ; i=i+2) {
            time+=n[i+1];
            pass[i]=true;
            pass[i+1]=true;
            if(time!=0){
                for (int j = 0; j <n.length ; j++) {
                    if(pass[j]==true){
                        time+=n[j];
                        pass[j]=false;
                        break;
                    }
                }
            }
        }
        while(pass[0]==false){
            for (int i = pass.length-1; i >0; i--) {
                if(pass[i]==false&&pass[0]==false){
                    time+=n[i];
                    pass[i]=true;
                    pass[0]=true;
                }else if(pass[i]==false&&pass[0]==true){
                    time+=n[0];
                    pass[0]=false;
                    time+=n[i];
                    pass[0]=true;
                    pass[i]=true;
                }

            }
        }
        return time;
    }
    public static void main(String[] args) {
     CrossBridge bridge=new CrossBridge();
     int[] person=bridge.Init();
        System.out.println(bridge.solution(person));


    }
}
