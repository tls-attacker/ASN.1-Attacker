/*
 * Copyright 2023 ic0ns.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.rub.nds.asn1.handler;

import de.rub.nds.asn1.context.EmptyChooser;
import org.junit.jupiter.api.Test;

public class EmptyHandlerTest {
    
    /**
     * Test of adjustContext method, of class EmptyHandler.
     */
    @Test
    public void testAdjustContext() {
        EmptyHandler instance = new EmptyHandler(new EmptyChooser());
        instance.adjustContext();
        //This should do nothing
    }
    
}
