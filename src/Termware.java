/**
 * Created by Chinmoy_M on 4/10/2017.
 */
public class Termware {
    public static void main(String args[]) {
    String[] args = {"iReduce"};
    try {
	TermWare.getInstance().init(args);
    ITermRewritingStrategy strategy=new FirstTopStrategy();
    IFacts facts=new DefaultFacts();
    TermSystem termSystem=new TermSystem(strategy,facts,TermWare.getInstance());
	termSystem.addRule("x->y");
	termSystem.addRule("y->z");
    Term inputTerm=TermWare.getInstance().getTermFactory().createAtom("x");
    Term outputTerm=termSystem.reduce(inputTerm);
	if(outputTerm.getName().equals("z")){
        Log.d("iReduce Termware","success");
    }
}catch(TermWareException ex){
        Log.e("iReduce Termware", "eror:"+ex.getMessage());
        ex.printStackTrace();
        }
    }

        }
