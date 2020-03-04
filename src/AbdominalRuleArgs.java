/**
 * Created by Chinmoy_M on 4/10/2017.
 */
    public class AbdominalRuleArgs extends RuleArgs{
        public enum ArgField
        {
            User
            , right_lower_abdomen
            , left_lower_abdomen
            , pain_nausea
            , blood_in_stool
            , blood_in_urine
        };
        public String getUser()
        {
            return getString(ArgField.User);
        }
        public void setUser(String user)
        {
            setString(ArgField.User, user);
        }
    }
    public abstract class AbdominalRule implements Rule{
        @Override
        public boolean passes(RuleArgs ruleArgs) {
            // TODO Auto-generated method stub
            if (false == (ruleArgs instanceof AbdominalRuleArgs))
            {
                Log.msg("ruleArgs must be an instance of AbdominalRuleArgs ");
                return false;
            }
            // Cast RuleArgs to AbdominalRuleArgs and validate
            AbdominalRuleArgs abArgs = (AbdominalRuleArgs) ruleArgs;
            // Muse have all args set
            if (false == abArgs.isright_lower_abdomenSet()
                    || false == abArgs.isleft_lower_abdomenSet()
                    || false == abArgs.isUserSet()
                    || false == abArgs.ispain_nauseaSet()
                    || false == abArgs.isblood_in_stoolSet()
                    || false == abArgs.isblood_in_urineSet()
                    )
            {
                Log.msg("Not all the arguments in AbdominalRuleArgs are set.");
                return false;
            }
            // If all args are there, let the child class do its evaluation
            return passes(abArgs);
        }
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // Get the config file as an InputStream
            InputStream is =
                    Main.class.getClassLoader().getResourceAsStream(
                            "roolie/abdominal/roolie-config.xml");
            RulesEngine rules = new RulesEngine(is);
            // Create some rule arguments (aka "Facts") to test for some users
            List<AbdominalRuleArgs> abdominalRuleArgsList = createRuleArgsToTest();
            // See if rules pass for each BankingRuleArgs created.
            for (AbdominalRuleArgs ruleArgs : abdominalRuleArgsList)
            {
                msg("\n* Evaluating " + ruleArgs.getUser()+"'s health:\n");
                boolean isUltrasound =rules.passesRule("Ultrasound", ruleArgs);
                boolean isCTScan =rules.passesRule("CTScan", ruleArgs);
                boolean isStoolTest1 =rules.passesRule("StoolTest1", ruleArgs);
                boolean isStoolTest2 =rules.passesRule("StoolTest2", ruleArgs);
                boolean isStoolTest3 =rules.passesRule("StoolTest3", ruleArgs);
                boolean isNothing =rules.passesRule("NoTest", ruleArgs);
            }
        }

    }
