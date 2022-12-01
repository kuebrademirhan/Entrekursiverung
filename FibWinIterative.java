public class FibWinIterative {
    public static long fibWinIterative(FibWinHelper fwh, int x, int win){
        if(x<win+1){
            return x;
        }
        fwh.allocateArray(win+2);

        for(int i=0;i<=win;i++){
            fwh.setArrayValue(i,i);
        }
        fwh.setArrayValue(win+1, fwh.getArrayValue(0)+fwh.getArrayValue(win));
        fwh.setArrayValue(0, fwh.getArrayValue(win+1));

        int counter=win+2;  //for the values that are bigger than win,but smaller equal than x

        int inx1=0;
        int inx2=1;
        int temp=0;
        while(counter<=x){
            if(inx1<win+1){
                fwh.setArrayValue(inx2, fwh.getArrayValue(inx1)+ fwh.getArrayValue(inx2));
                inx1++;
                inx2++;

            }else{
                inx1=0;
                inx2=1;
            }
           counter++;
        }

        return fwh.getArrayValue(win+1);
    }

}
