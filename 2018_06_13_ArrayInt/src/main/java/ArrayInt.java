public class ArrayInt {
    private int[] ar;
    private boolean flSorted;
    public int size;
    private static final int INITIAL_CAPCITY = 16;
    private static final int ARRAY_SIZE_MULTIPLIER=2;

    public int[] getAr() {
        return ar;
    }

    public ArrayInt(int[] ar) {
        if (ar==null){
            return;
        }

        if (ar.length<=INITIAL_CAPCITY){
            this.ar = new int[INITIAL_CAPCITY];
        } else {
            this.ar = new int[ar.length];
        }

        if (ar.length==0){
            return;
        }

        this.size=ar.length;


        this.ar[0]=ar[0];
        flSorted=true;
        for (int i=1; i<ar.length; i++) {
           this.ar[i] = ar[i];
            if (this.ar[i - 1] > ar[i]) {
                this.flSorted=false;
            }
        }
        int a=0;
    }

    public int search(int value) {
        return this.flSorted ? binarySearch(value) : linearSearch(value);
    }

    private int linearSearch(int value) {
        for (int i=0; i<this.size;i++){
            if (ar[i]==value){
                return i;
            }
        }
        return -this.size;
    }

    private int binarySearch(int value) {
        int index=-1;
        int low=0;
        int high=this.size-1;
        while (low <= high) {
           int mid = (high+low) / 2;
            if (this.ar[mid] < value) {
                low = mid + 1;
            } else if (this.ar[mid] > value) {
                high = mid - 1;
            } else if (this.ar[mid] == value) {
                index = mid;
                break;
            }
        }
        return index==-1?-(low+1):index;
    }


    public Integer getValue(int index) {
        return (index < this.size && index>=0) ? this.ar[index]: null;
    }

    public void addValue(int value){
        if (this.size>=this.ar.length){
            enlargeArray();
        }
        if (flSorted){
            addValueByIndex(binarySearch(value), value);
        } else {
            this.ar[size]=value;
        }
        size++;

    }

    private void copyArray(int[] destArray){

        for (int i=0;i<this.ar.length;i++){
            destArray[i] = this.ar[i];
        }
        this.ar=destArray;
    }

    private void enlargeArray() {
        int[] ar1=new int[this.ar.length*ArrayInt.ARRAY_SIZE_MULTIPLIER];
        copyArray(ar1);
    }

    private void addValueByIndex(int index, int value) {
       int idx=-index-1;
        int tmp;
        int tmp1;
        tmp = this.ar[idx];
        for (int i=idx;i<this.size;i++){
            tmp1=this.ar[i+1];
            this.ar[i+1]=tmp;
            tmp=tmp1;
      }
      this.ar[idx]=value;
    }

    public Integer deleteValue(int index) {


        return null;
    }
}