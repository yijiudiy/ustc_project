package pddbishi;

import java.util.*;

public class Main {
        static class TempClass{
            int pos;
            int val;
            public TempClass(int pos,int val){
                this.pos=pos;
                this.val=val;
            }

        public static void main(String[] args) {
            Scanner scanner=new Scanner(System.in);
            int n=scanner.nextInt();
            int d=scanner.nextInt();
            List<TempClass> list=new ArrayList<>();
            for(int i=0;i<n;i++){
                list.add(new TempClass(scanner.nextInt(),scanner.nextInt()));
            }
            Collections.sort(list, new Comparator<TempClass>() {
                @Override
                public int compare(TempClass o1, TempClass o2) {
                    if(o1.val<o2.val)
                        return 1;
                    else if(o1.val>o2.val)
                        return -1;
                    else
                        return 0;
                }
            });
            long maxMoney=0;
            int T=list.size();
            for(int i=0;i<list.size();i++){
                long currentVal=list.get(i).val;
                for(int j=i+1;j<T;j++){
                    if(Math.abs(list.get(j).pos-list.get(i).pos)>=d){
                        if(list.get(j).val+currentVal>maxMoney){
                            maxMoney=list.get(j).val+currentVal;
                            T=j;
                            break;
                        }
                    }
                }
            }
            System.out.println(maxMoney);
        }
    }
}