package 洗牌;

public class shuffle {
    public static void main(String[] args) {
        int [] a = new int[10];
        for(int i=0; i<10; i++){
            a[i] = i+1;
        }
        for(int i=0; i<10; i++){
            System.out.print(a[i] + "  ");
        }
        System.out.println();
        shuffle(a);

        for(int i=0; i<10; i++){
            System.out.print(a[i] + "  ");
        }

    }
    public static void swap(int[] a , int i , int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    public static void shuffle(int[] a){
        for(int i=0; i<a.length; i++){
            int j = (int)(Math.random()*a.length);
            swap(a,i,j);
        }
    }
}
