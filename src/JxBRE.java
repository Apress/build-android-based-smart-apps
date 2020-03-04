/**
 * Created by Chinmoy_M on 4/10/2017.
 */
public class JxBRE {
    public static void main(String args[]) {
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            bre = new BREImpl();
            args=new String[]{ "/data/data/" +packageName +"/files/abdominal.xml"};
            //args="-s D:\\android_training\\rules\\discount.xml";
            setContentView(R.layout.main);
            copyCLPFiles("abdominal.xml");
            copyCLPFiles("businessRules.xsd");
            AbdominalMainLoad(args);
            Inputs order = new Inputs();
            getTotal(order);
        }
        private void copyCLPFiles(String fileName) {
            try {
                File file = getFileStreamPath(fileName);
                if(file.exists()) {
                    return;
                }
                else  {
                    InputStream myInput = getAssets().open(fileName);
                    OutputStream myOutput = new FileOutputStream(
                            "/data/data/" +
                                    getPackageName() +"/files/"+fileName);
                    //transfer bytes from the inputfile to the outputfile
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = myInput.read(buffer))>0){
                        myOutput.write(buffer, 0, length);
                    }
                    //Close the streams
                    myOutput.flush();
                    myOutput.close();
                    myInput.close();
                }
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public void AbdominalMainLoad(String[] args) {
            try {
                Document doc = loadFile(args[0]);
                // Lets register as a listener....
                ((BREImpl)bre).addListener(this);
                ((BREImpl)bre).init(doc);
            }
            catch (Exception e) {
                System.err.println("Could not create document");
                e.printStackTrace();
            }
        }
        /**
         * Lets pretend that we have an Object called Order and it has all
         * relevant order information including an Object for the Product that
         * is ordered
         */
        public void getTotal(Inputs aOrder) {
            // Have to do this so the anonymous classes can get to it..
            inp = aOrder;
            BRERuleContext aBRC = bre.getRuleContext();
            /**
             * This is the bast way to do this.  Better than wrapper classes.
             * Don't know why I didn't think of this earlier....
             */
            aBRC.setFactory(BLOOD_IN_URINE, new BRERuleFactory() {
                public Object executeRule(BRERuleContext aBrc, Map aMap, Object aStep) {
                    return inp.getBlood_in_urine();
                }
            });
            aBRC.setFactory(BLOOD_IN_STOOL, new BRERuleFactory() {
                public Object executeRule(BRERuleContext aBrc, Map aMap, Object aStep) {
                    return inp.getBlood_in_stool();
                }
            });
            aBRC.setFactory(RIGHT_LOWER_ABDOMEN, new BRERuleFactory() {
                public Object executeRule(BRERuleContext aBrc, Map aMap, Object aStep) {
                    return inp.getRight_lower_abdomen();
                }
            });
            aBRC.setFactory(LEFT_LOWER_ABDOMEN, new BRERuleFactory() {
                public Object executeRule(BRERuleContext aBrc, Map aMap, Object aStep) {
                    return inp.getLeft_lower_abdomen();
                }
            });
            aBRC.setFactory(PAIN_NAUSEA, new BRERuleFactory() {
                public Object executeRule(BRERuleContext aBrc, Map aMap, Object aStep) {
                    return inp.getPain_nausea();
                }
            });
            aBRC.setFactory(RECCTEST_BLOODURINE, new BRERuleFactory() {
                public Object executeRule(BRERuleContext aBrc, Map aMap, Object aStep) {
                    return DecisionString.getDecisionString( new String((String)aMap.get(TEST1)) );
                }
            });
            aBRC.setFactory(RECCTEST_RIGHTLOWER, new BRERuleFactory() {
                public Object executeRule(BRERuleContext aBrc, Map aMap, Object aStep) {
                    return DecisionString.getDecisionString( new String((String)aMap.get(TEST2)) );
                }
            });
            aBRC.setFactory(RECCTEST_LEFTLOWER, new BRERuleFactory() {
                public Object executeRule(BRERuleContext aBrc, Map aMap, Object aStep) {
                    return DecisionString.getDecisionString( new String((String)aMap.get(TEST3)) );
                }
            });
            aBRC.setFactory(NOTHING, new BRERuleFactory() {
                public Object executeRule(BRERuleContext aBrc, Map aMap, Object aStep) {
                    return DecisionString.getDecisionString( new String((String)aMap.get(TEST4)) );
                }
            });
            //bre.process();
            bre.process("SET1");
            bre.process("SET2");
            bre.process("SET3");
            bre.process("SET4");
            //System.out.println(bre.getRuleContext().toString());
            if ((aBRC.getResult(RECCTEST_BLOODURINE))!=null) {			System.out.println((String)aBRC.getResult(RECCTEST_BLOODURINE).getResult());
            }
            else {}
            if ((aBRC.getResult(RECCTEST_RIGHTLOWER))!=null) {
                System.out.println((String)aBRC.getResult(RECCTEST_RIGHTLOWER).getResult());
            }
            else {}
            if ((aBRC.getResult(RECCTEST_LEFTLOWER))!=null) {			System.out.println(aBRC.getResult(RECCTEST_LEFTLOWER).getResult());
            }
            else {}
            if (aBRC.getResult(NOTHING)!=null) {
                System.out.println(aBRC.getResult(NOTHING).getResult());
            }

        }

    }
}
