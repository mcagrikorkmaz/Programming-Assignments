import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        String line;
        BufferedReader decimalReader = new BufferedReader(new FileReader(args[0]));
        PrintWriter outWriter = new PrintWriter(new FileWriter("octal.txt"));
        while((line = decimalReader.readLine()) != null){
            int decimalNumber = Integer.parseInt(line.trim());
            Stack<Integer> remainStack = new Stack<>();
            while(decimalNumber != 0){
                remainStack.push(decimalNumber % 8);
                decimalNumber = decimalNumber / 8;
            }
            int size = remainStack.size()-1;
            for(int i = 0 ; i <= size ; i++){
                outWriter.print(remainStack.pop());
            }
            outWriter.println();
        }
        decimalReader.close();
        outWriter.close();
    }
}
