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

package luis.sample.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.security.saml.userdetails.SAMLUserDetailsService;
import org.opensaml.saml2.core.Attribute;
import org.springframework.stereotype.Service;

@Service
public class SAMLUserDetailsServiceImpl implements SAMLUserDetailsService {
	
	// Logger
	private static final Logger LOG = LoggerFactory.getLogger(SAMLUserDetailsServiceImpl.class);
	
	public Object loadUserBySAML(SAMLCredential credential)
			throws UsernameNotFoundException {
		
		// The method is supposed to identify local account of user referenced by
		// data in the SAML assertion and return UserDetails object describing the user.
		
		String userID = credential.getNameID().getValue();
		
		LOG.info(userID + " is logged in");


		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
                String[] values = credential.getAttributeAsStringArray("roles");
                if (values != null) {
                    for(String s : values){
			GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+s);
			authorities.add(authority);
                    }        
                }
		if (authorities.size() == 0) {
                    // default ROLE
		    GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
		    authorities.add(authority);
		}

		// In a real scenario, this implementation has to locate user in a arbitrary
		// dataStore based on information present in the SAMLCredential and
		// returns such a date in a form of application specific UserDetails object.
		return new User(userID, "<abc123>", true, true, true, true, authorities);
	}
	
}

