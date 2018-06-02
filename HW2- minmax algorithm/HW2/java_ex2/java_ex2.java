import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class java_ex2 {
    //this class is the main function that get input
    // and return output file with who is going to win


    //main function
    public static void main(String[] args) {

        char[][] grid = new char[5][5];
        int counterLines = 0;

        BufferedReader br = null;
        FileReader fr = null;
//read from file
        try {

            //br = new BufferedReader(new FileReader(FILENAME));
            fr = new FileReader("input.txt");//TODO change to input regular
            br = new BufferedReader(fr);

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                // System.out.println(sCurrentLine);


                for (int i = 0; i < 5; i++) {
                    grid[counterLines][i] = sCurrentLine.charAt(i);
                    //System.out.println("current tav    "+grid[lineInGrid][i]);

                }
                counterLines++;
            }
        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }
        board b = new board(grid);
        minimax minimaxAlgo= new minimax();
        char numOfMyPlayer='B';


        while (b.checkGameOver()!=1) {
            int[] a = minimaxAlgo.minimax(b, 3, true, numOfMyPlayer);
            b.updateMyBoard(a[1],a[2],numOfMyPlayer);
            if(numOfMyPlayer=='B') {
                numOfMyPlayer = 'W';
            }else {numOfMyPlayer='B';}
        }
        char answer=b.WhoIsWin();

        //System.out.println(myPath);

        //write to file
        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("output.txt"), "utf-8"));
            writer.write(answer);
        } catch (IOException ex) {
            // report
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {/*ignore*/}
        }
    }

}



