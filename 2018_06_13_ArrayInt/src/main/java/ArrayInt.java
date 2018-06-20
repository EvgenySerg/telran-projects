import java.util.Random;

public class ArrayInt {
    private int[] ar;
    private boolean flSorted;
    public int size;
    private static final int INITIAL_CAPCITY = 16;
    private static final int ARRAY_SIZE_MULTIPLIER=2;
    Random rnd=new Random(System.nanoTime());


    public int[] getAr() {
        return ar;
    }

    public boolean isSorted(){
        return flSorted;
    }

    public int getSize() {
        return size;
    }

    public ArrayInt() {
        this.ar = new int[INITIAL_CAPCITY];
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
            insertToSortedArray(value);
        } else {
           insertToUnsortedArray(value);
        }
        size++;

    }

    private void insertToUnsortedArray(int value){
        this.ar[this.size]=value;
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

    private void insertToSortedArray(int value) {
        int idx=binarySearch(value);
        if (idx<0){
            idx=-idx-1;
        }

        int tmp, tmp1;
        tmp = this.ar[idx];
        for (int i=idx;i<this.size;i++){
            tmp1=this.ar[i+1];
            this.ar[i+1]=tmp;
            tmp=tmp1;
      }
      this.ar[idx]=value;
    }

    /**
     * Removes number at given index
     * @param index
     * @return true idf index is correct
     */
    public boolean remove(int index){
        if (index<0 && index>=size){
            return false;
        }

        return true;
    }








    public Integer deleteValue(int value) {
        int idx;
        if (flSorted){
            idx=binarySearch(value);
        } else {
            idx=search(value);
        }
        if (idx < 0) {
            return null;
        }

        return !deleteValueByIndex(idx)?null:idx;
    }

    private boolean deleteValueByIndex(int index) {
        if (index<=0){
            return false;
        }
        for (int i = index+1; i < size; i++) {
               ar[i-1]=ar[i];
        }
        //ar[size-1]=0;
        size--;
        return true;
    }

    /**
     * mix order of numbers in the random order
     */
    public void shuffle() {
        double k=1;
        double desiredKValue=0.1;
        do {
            for (int i = this.size-1; i >0; i--) {
                int index = (int) (rnd.nextDouble() * this.size);
                int tmp = this.ar[i];
                this.ar[i] = this.ar[index];
                this.ar[index] = tmp;
            }
            k=getShuffleCorrelationKoefficient();
        }
        while ((k<0?-k:k) > desiredKValue) ;
    }

    public double getShuffleCorrelationKoefficient(){
        double size=this.size;
        double avgY = 0, avgX = 0, drobX = 0, drobY = 0;

        for (int i = 0; i < size; i++) {
            avgY += (double) getValue(i) / size;
            avgX += (double) i / size;
        }
        for (int i = 0; i < size; i++) {
            drobX += Math.pow((double) i - avgX, 2);
            drobY += Math.pow(getValue(i) - avgY, 2);
        }

        double sigmaX = Math.sqrt(1 / (size - 1) * drobX);
        double sigmaY = Math.sqrt(1 / (size - 1) * drobY);
        double koefKor = (1 / (size - 2));
        double sumForKoef = 0;
        for (int i = 0; i < size; i++) {
            sumForKoef += (((double) i - avgX) / sigmaX) * (getValue(i) - avgY) / sigmaY;
        }
        koefKor = koefKor * sumForKoef;
       // System.out.println(koefKor);

        return koefKor;
    }

    public void sort() {


    }
}