/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package luis.sample.controllers;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.security.core.userdetails.User;
import org.opensaml.saml2.core.Attribute;
import org.opensaml.xml.schema.XSString;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.*;

@RunWith(SpringRunner.class)
public class TestLoginController{
    
    private final String NAME = "NAME";

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testLandingOK() throws Exception {
        LoginController loginController = Mockito.spy(LoginController.class);
        User user = Mockito.mock(User.class);
        Mockito.doReturn(NAME).when(user).getUsername();
        Assert.assertEquals(((ResponseEntity)loginController.landing(user)).getStatusCode(), HttpStatus.OK);
    }


    @Test
    public void testDetailsNOK() throws Exception {
        LoginController loginController = Mockito.spy(LoginController.class);
        Mockito.doReturn(null).when(loginController).getAuthentication();

        Assert.assertEquals(((ResponseEntity)loginController.details()).getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testDetailsOK() throws Exception {
        LoginController loginController = Mockito.spy(LoginController.class);
        Authentication samlAuth = Mockito.mock(Authentication.class);
        SAMLCredential credential = Mockito.mock(SAMLCredential.class);
        Attribute attribute = Mockito.mock(Attribute.class);
        List<Attribute> attributes = new ArrayList();
        attributes.add(attribute); 
        XSString value = Mockito.mock(XSString.class);
        List<XSString> attributeVals = new ArrayList();
        attributeVals.add(value); 

        Mockito.doReturn(samlAuth).when(loginController).getAuthentication();
        Mockito.doReturn(credential).when(samlAuth).getCredentials();
        Mockito.doReturn(NAME).when(attribute).getName();
        Mockito.doReturn(attributes).when(credential).getAttributes();
        Mockito.doReturn(attributeVals).when(attribute).getAttributeValues();

        Assert.assertEquals(((ResponseEntity)loginController.details()).getStatusCode(), HttpStatus.OK);
    }


}
