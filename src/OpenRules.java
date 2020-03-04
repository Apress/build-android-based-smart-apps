/**
 * Created by Chinmoy_M on 4/10/2017.
 */
public class OpenRules {
    public static void main(String args[]) {
        UserInput userInput=new UserInput("no","yes","yes","no","yes");
        Response response=new Response();
        String fileName = "file:sdcard/openrules.config/DecisionOneOrTwo.xls";
        Decision decision = new Decision("DecisionAbdominalPain",fileName);
        decision.put("userInput", userInput);
        decision.put("response", response);
        decision.execute();

    }
}
