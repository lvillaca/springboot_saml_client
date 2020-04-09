/*
 * Copyright 2019 Vincenzo De Notaris
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */

package luis.sample.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.saml.SAMLCredential;
import org.opensaml.saml2.core.Attribute;
import org.opensaml.xml.schema.XSString;

import transpetro.arqinfo.util.CurrentUser;

@Controller
public class LoginController {
	
	// Logger
	private static final Logger LOG = LoggerFactory
			.getLogger(LoginController.class);

	@RequestMapping("/hasLogged")
	public ResponseEntity<String> landing(@CurrentUser User user, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			LOG.debug("Current authentication instance from security context is null");
                        return new ResponseEntity<String>("Not ok",HttpStatus.BAD_REQUEST);
		}  
		LOG.debug("User SAML Attributes : ");

        ((SAMLCredential)auth.getCredentials()).getAttributes().forEach( attribute ->
          ((Attribute)attribute).getAttributeValues().forEach( xsString ->
              LOG.debug(((Attribute)attribute).getName()+":"+((XSString) xsString).getValue())));
              
        return new ResponseEntity<String>("{\"user\":\""+user.getUsername()+"\"}",HttpStatus.OK);
	}
      
        @RequestMapping("/details")
        public ResponseEntity<String> details(@CurrentUser User user, Model model) {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                if (auth == null) {
                        LOG.debug("Current authentication instance from security context is null");
                        return new ResponseEntity<String>("Not ok",HttpStatus.BAD_REQUEST);
                }
                LOG.debug("User SAML Attributes : ");

                StringBuilder appender = new StringBuilder();

                appender.append("{");

                ((SAMLCredential)auth.getCredentials()).getAttributes().forEach( attribute ->
                  ((Attribute)attribute).getAttributeValues().forEach( xsString -> {
                           if (appender.indexOf(":")!=-1) appender.append(", ");
                           appender.append("\"");
                           appender.append(((Attribute)attribute).getName());
                           appender.append("\":\"");
                           appender.append(((XSString) xsString).getValue());
                           appender.append("\"");
                      }));

                appender.append("}");

                return new ResponseEntity<String>(appender.toString(),HttpStatus.OK);
        }

}
