/**
* OWASP WebGoat Benchmark Edition (WBE) v1.1
*
* This file is part of the Open Web Application Security Project (OWASP)
* WebGoat Benchmark Edition (WBE) project. For details, please see
* <a href="https://www.owasp.org/index.php/WBE">https://www.owasp.org/index.php/WBE</a>.
*
* The WBE is free software: you can redistribute it and/or modify it under the terms
* of the GNU General Public License as published by the Free Software Foundation, version 2.
*
* The WBE is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
* even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details
*
* @author Dave Wichers <a href="https://www.aspectsecurity.com">Aspect Security</a>
* @created 2015
*/

package org.owasp.webgoat.benchmark.testcode;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BenchmarkTest13325")
public class BenchmarkTest13325 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String param = request.getQueryString();

		String bar = new Test().doSomething(param);
		
	    try {
		    java.util.Random numGen = java.security.SecureRandom.getInstance("SHA1PRNG");
		
		    // Get 40 random bytes
		    byte[] randomBytes = new byte[40];
		    getNextNumber(numGen, randomBytes);
			response.getWriter().println("Random bytes are: " + new String(randomBytes));
				    
	    } catch (java.security.NoSuchAlgorithmException e) {
			System.out.println("Problem executing SecureRandom.nextBytes() - TestCase");
			throw new ServletException(e);
	    } finally {
			response.getWriter().println("Randomness Test java.security.SecureRandom.nextBytes(byte[]) executed");	    
	    }
	}
	    	
	void getNextNumber(java.util.Random generator, byte[] barray) {
		generator.nextBytes(barray);
	}  // end doPost

    private class Test {

        public String doSomething(String param) throws ServletException, IOException {

		String bar = "safe!";
		java.util.HashMap<String,Object> map19920 = new java.util.HashMap<String,Object>();
		map19920.put("keyA-19920", "a_Value"); // put some stuff in the collection
		map19920.put("keyB-19920", param.toString()); // put it in a collection
		map19920.put("keyC", "another_Value"); // put some stuff in the collection
		bar = (String)map19920.get("keyB-19920"); // get it back out
		bar = (String)map19920.get("keyA-19920"); // get safe value back out

            return bar;
        }
    } // end innerclass Test

} // end DataflowThruInnerClass