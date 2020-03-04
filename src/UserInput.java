/**
 * Created by Chinmoy_M on 4/10/2017.
 */
public class UserInput {
    String right_lower_abdomen;
    String left_lower_abdomen;
    String pain_nausea;
    String blood_in_stool;
    String blood_in_urine;
    public UserInput(String rla,String lla,String pn,String bis,String biu){
        this.right_lower_abdomen =rla;
        this.left_lower_abdomen =lla;
        this.pain_nausea =pn;
        this.blood_in_stool =bis;
        this.blood_in_urine =biu;
    }
    public String getRight_lower_abdomen() {
        return right_lower_abdomen;
    }
    public void setRight_lower_abdomen(String right_lower_abdomen) {
        this.right_lower_abdomen = right_lower_abdomen;
    }

}
