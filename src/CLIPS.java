/**
 * Created by Chinmoy_M on 18/07/2017.
 */
public class CLIPS {

    public static void main(String args[]) {
        try {
            System.loadLibrary("CLIPSJNI");
            if(clips == null) {
                clips = new Environment();
            }
        }
        catch(UnsatisfiedLinkError ule) {
            Log.e("JNI", "Could not load libCLIPSJNI.so!");
        }
        private  static Environment clips = null;
        static {
            try {
                if(clips == null) {
                    clips = new Environment();
                }
                clips.clear();
                clips.load("diarrhoea.clp”);
                        String myassertString = "(diarrhea_data (diarrhea yes) " +
                                "(blood_in_stool "+mydiarrheaData.sBlood_In_Stool +") " +
                                "(how_many_days "+mydiarrheaData.iNumberofDays+") " +
                                "(lethargic_unconscious "+mydiarrheaData.sLethargic_Unconscious+") "+
                                "(restless_irritable "+mydiarrheaData.sRestless_Irritable+") " +
                                "(sunken_eyes "+ mydiarrheaData.sSunken_Eyes+") " +
                                "(skin_pinch_veryslow "+mydiarrheaData.sSkin_Pinch_Veryslow+") " +
                                "(skin_pinch_slow "+mydiarrheaData.sSkin_Pinch_Slow+") " +
                                "(not_able_to_drink_or_drinking_poorly "+mydiarrheaData.sNot_Able_To_Drink_or_Drinking_Poorly+") " +
                                "(drinking_eagerly_or_thirsty "+mydiarrheaData.sDrinking_Eagerly_or_Thirsty+") " +
                                "(other_severe_disease " + otherSevereDisease + "))";

                clips.assertString(myassertString);
                clips.run();
                MultifieldValue  mv = (MultifieldValue) clips.eval(“?*WHODecisionCode*”);
                String WHODecision;
                List theList = mv.listValue();
                for(Iterator itr = theList.iterator(); itr.hasNext();)
                {
                    StringValue myValue = (StringValue) itr.next();
                    WHODecision = WHODecision +  myValue.toString() + " ";

                }


            }

}
    }
}
