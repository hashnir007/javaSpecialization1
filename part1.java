import edu.duke.*;
import java.io.*;

public class part1{

    public int findStopCodon(String dna, int startIndex, String stopCodon){

       
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while(currIndex !=-1){
            int diff = currIndex - startIndex;
            if(diff % 3 == 0){
                return currIndex;
            }
            else{
                currIndex = dna.indexOf(stopCodon, currIndex +1);
            }
        }
        return -1;
    }
    
        public int minIndex(int a, int b, int c){
            if(a<0 && b<0 && c<0){
                return -1;
            }
            
            if(a <0 && b<0 && c>=0){
                return c;
            }
            if(a <0 && c<0 && b>=0){
                return b;
            }
            if(b <0 && c<0 && a>=0){
                return a;
            }
            
            if(a <0 && c>=0 && b>=0){
                return Math.min(c,b);
            }
            if(b <0 && c>=0 && a>=0){
                return Math.min(c,a);
            }
            if(c <0 && b>=0 && a>=0){
                return Math.min(b,a);
            }
            if(c>=0 && b>=0 && c>=0){
                return Math.min(a, Math.min(b, c));
            }
            //return dna.length();
            return -1;
        }
    public String findGene(String dna, int where){
        //System.out.println("inside findGene");
            int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }
        //System.out.println("start index "+startIndex);
        int taaTag = findStopCodon(dna, startIndex, "TAA");
        int tgaTag = findStopCodon(dna, startIndex, "TGA");
        int tagTag = findStopCodon(dna, startIndex, "TAG");
        int minIndex = minIndex(taaTag, tgaTag, tagTag);
        //System.out.println(taaTag +" "+tgaTag+" "+tagTag);
        //System.out.println("min index "+minIndex);
        if (minIndex >= 0 && minIndex !=dna.length()){
           // System.out.println("inside returnig dna");
            return dna.substring(startIndex, minIndex + 3);
        }
        else{
            return "";
        }
    }

    public StorageResource getAllGenes(String dna){
        StorageResource geneList = new StorageResource();
       // System.out.println("inside getAllGenes");
        int startIndex = 0;
        while(true){
            String currentGene =findGene(dna, startIndex);
           // System.out.println(currentGene);
            if(currentGene.isEmpty()){
                break;
            }
            else{
                geneList.add(currentGene);
                startIndex = dna.indexOf(currentGene,startIndex) + currentGene.length();
            } 
       } return geneList;
    }

    public float cgRatio(String dna) {
        int count = 0;
        int strLen = dna.length();

        for(int i = 0; i < strLen; i++) {
            if(dna.charAt(i) == 'C' || dna.charAt(i) == 'G') {
                count++;
            }
        }

        float ratio =(float) count / (float)strLen;
        return ratio;
    }
    
    public int ctg(String dna) {
        int count = 0;
        int strLen = (dna.length()-1);
        System.out.println(strLen);
        for(int i = 0; i <strLen -3; i++) {
            
            char a = dna.charAt(i);
            char b = dna.charAt(i+1);
            char c =dna.charAt(i+2);
            
            if(a=='C' && b=='T' && c=='G'){
                count++;
            }
        }
        
        return count;
    }

    public void howMany(StorageResource r){
            int sixty =0;
            int point35=0;
            int gene = 0;
            int longest =0;

            for (String g : r.data()) {
                System.out.println("");
                gene = gene + 1;
                System.out.println("No."+gene);
                System.out.println("gene:"+g);                
                
                int len= g.length();
                System.out.println("len:"+len);
                if(len>60){    
                    sixty += 1;
                    System.out.println("sixty updated:"+sixty);
                }
                if (len> longest)
                    {
                        longest = g.length();
                        System.out.println("longest updated:"+longest);
                }

                float cgRatio = cgRatio(g);
                System.out.println("cgRatio:"+cgRatio);
            
                if (cgRatio(g)>0.35) {
                    point35 +=1;
                    System.out.println("point35 updated:"+point35);
                }
            }

            System.out.println("genes:"+gene);
            System.out.println("sixty :"+ sixty);
            System.out.println("0.35:"+point35);
            System.out.println("longest:"+ longest);
            
    }

    public static void main(String[] args) {
        part1 a = new part1();
        a.realTesting();    
    }


    public void realTesting() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String s = fr.asString();
            System.out.println("read " + (s.length()-1) + " characters");
            int x = ctg(s);
            System.out.println("ctg count "+x);
            StorageResource r = getAllGenes(s);
            howMany(r);
        }
    }
}   











    







