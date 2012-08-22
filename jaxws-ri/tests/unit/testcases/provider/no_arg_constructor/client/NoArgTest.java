/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2012 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * http://glassfish.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

/*
 * $Id: NoArgTest.java,v 1.1 2009-07-29 22:25:11 jitu Exp $
 */

/*
 * Copyright 2004 Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package provider.no_arg_constructor.client;

import junit.framework.*;
import testutil.ClientServerTestUtil;
import testutil.HTTPResponseInfo;

/**
 *
 * @author Jitendra Kotamraju
 */
public class NoArgTest extends TestCase {

    public NoArgTest(String name) throws Exception {
        super(name);
    }

    Hello getStub() throws Exception {
        return new Hello_Service().getHelloPort();
    }

    public void testSource() throws Exception {
        String message = "<s:Envelope xmlns:s='http://schemas.xmlsoap.org/soap/envelope/'><s:Body/></s:Envelope>";
        // running multiple times so that service returns SAXSource(),
        // DOMSource(), StreamSource()
        for(int i=0; i < 3; i++) {
            HTTPResponseInfo rInfo = ClientServerTestUtil.sendPOSTRequest(getStub(),message);
            assertEquals(200, rInfo.getResponseCode());
        }
    }

}
