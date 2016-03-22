package com.cisco.gis.service;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

public class ldap {
		 
//	    public static void main(String[] args) {
//	       ldap retrieveUserAttributes = new ldap();
//	        retrieveUserAttributes.getUserBasicAttributes("dhsanghv", retrieveUserAttributes.getLdapContext());
//	    }
	 
	    public LdapContext getLdapContext(){
	        LdapContext ctx = null;
	        try{
	            Hashtable<String,String> env = new Hashtable<String,String>();
	            env.put(Context.INITIAL_CONTEXT_FACTORY,
	                    "com.sun.jndi.ldap.LdapCtxFactory");
	            env.put(Context.SECURITY_AUTHENTICATION, "Simple");
	            env.put(Context.SECURITY_PRINCIPAL, "CN=ccixlae.gen,OU=Generics,OU=Cisco Users,DC=cisco,DC=com");
	            env.put(Context.SECURITY_CREDENTIALS, "Cisco123$");
	            env.put(Context.PROVIDER_URL, "ldap://ds.cisco.com:389");
	            ctx = new InitialLdapContext(env, null);
	            System.out.println("Connection Successful.");
	        }catch(NamingException nex){
	            System.out.println("LDAP Connection: FAILED");
	            nex.printStackTrace();
	        }
	        return ctx;
	    }
	 
	    public Attributes getUserBasicAttributes(String username, LdapContext ctx) {
	        //User user=null;
	        try {
	 
	            SearchControls constraints = new SearchControls();
	            constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
	            String[] attrIDs = { "cn","sn","description","distinguishedname","givenname","telephonenumber",
	            		"othertelephone","manager","title","department","ciscoitdescription","streetaddress","mail",
	            		"street","l","postalcode","mobile","pager","physicaldeliveryofficename","st","co","middlename",
	            		"employeeid","facsimiletelephonenumber","postofficebox","ciscoitbuilding",
	            		"ciscoitfloor","ciscoitsite","ciscoitstatus","ciscoitmanaferuid","employeetype",
	            		"directreports","ciscoitinternalphonenumber","memberOf"};
	            
	    
	            constraints.setReturningAttributes(attrIDs);
	            //First input parameter is search bas, it can be "CN=Users,DC=YourDomain,DC=com"
	            //Second Attribute can be uid=username
	       
	           
	            NamingEnumeration answer = ctx.search("DC=cisco,DC=com", "cn="
	                    + username, constraints);
	            if (answer.hasMore()) {
	                Attributes attrs = ((SearchResult) answer.next()).getAttributes();
//	                System.out.println(attrs.get("cn"));
//	                System.out.println(attrs.get("sn"));
	                System.out.println(attrs.get("description"));
//	                System.out.println(attrs.get("distinguishedname"));
//	                System.out.println(attrs.get("givenname"));
//	                System.out.println(attrs.get("telephonenumber"));
//	                System.out.println(attrs.get("othertelephone"));
//	                System.out.println(attrs.get("manager"));
//	                System.out.println(attrs.get("title"));
//	                System.out.println(attrs.get("department"));
//	                System.out.println(attrs.get("ciscoitdescription"));
//	                System.out.println(attrs.get("streetaddress"));
//	                System.out.println(attrs.get("mail"));
//	                System.out.println(attrs.get("street"));
//	                System.out.println(attrs.get("l"));
//	                System.out.println(attrs.get("postalcode"));
//	                System.out.println(attrs.get("mobile"));
//	                System.out.println(attrs.get("pager"));
//	                System.out.println(attrs.get("physicaldeliveryofficename"));
//	                System.out.println(attrs.get("st"));
//	                System.out.println(attrs.get("co"));
//	                System.out.println(attrs.get("middlename"));
//	                System.out.println(attrs.get("employeeid"));
//	                System.out.println(attrs.get("facsimiletelephonenumber"));
//	                System.out.println(attrs.get("postofficebox"));
//	                System.out.println(attrs.get("ciscoitbuilding"));
//	                System.out.println(attrs.get("ciscoitfloor"));
//	                System.out.println(attrs.get("ciscoitsite"));
//	                System.out.println(attrs.get("ciscoitmanageruid"));
//	                System.out.println(attrs.get("employeetype"));
//	                System.out.println(attrs.get("directreports"));
//	                System.out.println(attrs.get("ciscoitinternalphonenumber"));
//	                System.out.println(attrs.get("memberOf"));
	                return attrs;
	            }else{
	                throw new Exception("Invalid User");
	            }
	 
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
			return null;
	    }
}
