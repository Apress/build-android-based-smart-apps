/**
 * Created by Chinmoy_M on 4/10/2017.
 */
public class Zilonis {
    public static void main(String args[]) {
        String fileName = "YOUR RULE FILE";
        FileInputStream fstream = new FileInputStream(fileName);
        int lineCount = getLines(fileName);
        System.out.println("line is "+ lineCount);
        DataInputStream dis = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(dis));
        ZilonisLexer lexer = new ZilonisLexer(dis);
        ZilonisParser parser = new ZilonisParser(lexer);
        GenericEventHandler geh = new GenericEventHandler(rete);
        parser.setEventHandler(geh);
        try {
            while(lineCount-- > 0) {
                parser.statement();
            }
        } catch (RecognitionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TokenStreamException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    } catch (FileNotFoundException e3) {
        // TODO Auto-generated catch block
        e3.printStackTrace();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e3.printStackTrace();
    }

}
}
