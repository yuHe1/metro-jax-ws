/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 1997-2012 Oracle and/or its affiliates. All rights reserved.
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

package wsa.w3c.fromwsdl.crinterop_s11.client;
import static wsa.w3c.fromwsdl.crinterop_s11.client.TestConstants.*;
import javax.xml.ws.soap.SOAPBinding;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPException;
import java.util.Calendar;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.io.IOException;


import javax.xml.ws.*;

/**
 * This class handles the non-anonymous responses
 * @author Rama Pulavarthi
 */
@WebServiceProvider
@ServiceMode(value = Service.Mode.MESSAGE)
public class NonAnonymousRespProcessor implements Provider<SOAPMessage> {
    Exchanger<SOAPMessage> msgExchanger;
    public NonAnonymousRespProcessor( Exchanger<SOAPMessage> msgExchanger) {
        this.msgExchanger = msgExchanger;
    }

    public SOAPMessage invoke(SOAPMessage request) {
        /*
        System.out.printf("====%s[start:%tc]====\n", getClass().getName(), Calendar.getInstance());
        try {
            request.writeTo(System.out);
        } catch (SOAPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("====%s[end:%tc]====\n", getClass().getName(), Calendar.getInstance());
        */
        try {
            msgExchanger.exchange(request, PROVIDER_MAX_TIMEOUT, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (TimeoutException e) {
            e.printStackTrace();
        }

        return null;
    }  
}
