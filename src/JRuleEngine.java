/**
 * Created by Chinmoy_M on 4/10/2017.
 */
public class JRuleEngine {
    public static void main(String args[]) {
        Class.forName( "org.jruleengine.RuleServiceProviderImpl" );
        String path    = Environment.getExternalStorageDirectory().getAbsolutePath()+"/temp/example3.xml";
        InputStream inStream = new FileInputStream( new File(path) );
        // Get the rule service provider from the provider manager.
        RuleServiceProvider serviceProvider = RuleServiceProviderManager.getRuleServiceProvider( "org.jruleengine" );
        // get the RuleAdministrator
        RuleAdministrator ruleAdministrator = serviceProvider.getRuleAdministrator();
        System.out.println("\nAdministration API\n");
        System.out.println( "Acquired RuleAdministrator: " + ruleAdministrator );
        // get an input stream to a test XML ruleset
        // This rule execution set is part of the TCK.
        //  InputStream inStream = new FileInputStream( "example3.xml" );
        System.out.println("Acquired InputStream to example3.xml: " + inStream );
        // parse the ruleset from the XML document
        RuleExecutionSet res1 = ruleAdministrator.getLocalRuleExecutionSetProvider(
                null ).createRuleExecutionSet( inStream, null );
        inStream.close();
        System.out.println( "Loaded RuleExecutionSet: " + res1);
        // register the RuleExecutionSet
        String uri = res1.getName();
        ruleAdministrator.registerRuleExecutionSet(uri, res1, null );
        System.out.println( "Bound RuleExecutionSet to URI: " + uri);
        // Get a RuleRuntime and invoke the rule engine.
        System.out.println( "\nRuntime API\n" );
        RuleRuntime ruleRuntime = serviceProvider.getRuleRuntime();
        System.out.println( "Acquired RuleRuntime: " + ruleRuntime );
        // create a StatefulRuleSession
        StatefulRuleSession statefulRuleSession =
                (StatefulRuleSession) ruleRuntime.createRuleSession( uri,
                        new HashMap(),
                        RuleRuntime.STATEFUL_SESSION_TYPE );
        System.out.println( "Got Stateful Rule Session: " + statefulRuleSession );
        // Add some clauses...
        ArrayList input = new ArrayList();
        input.add(new Clause("Socrate is human"));
        // add an Object to the statefulRuleSession
        statefulRuleSession.addObjects( input );
        System.out.println( "Called addObject on Stateful Rule Session: "
                + statefulRuleSession );
        statefulRuleSession.executeRules();
        System.out.println( "Called executeRules" );
        // extract the Objects from the statefulRuleSession
        List results = statefulRuleSession.getObjects();
        System.out.println( "Result of calling getObjects: " +
                results.size() + " results." );
        // Loop over the results.
        Iterator itr = results.iterator();
        while(itr.hasNext()) {
            Object obj = itr.next();
            System.out.println("Clause Found: "+obj.toString());
        }
        // release the statefulRuleSession
        statefulRuleSession.release();
        System.out.println( "Released Stateful Rule Session." );

    }
}
