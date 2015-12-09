/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.apache.jmeter.report.core;

/**
 * The class ControllerSamplePredicate provides a way to define whether a sample
 * is a controller.
 * 
 * @since 2.14
 */
public class ControllerSamplePredicate implements SamplePredicate {
    /*
     * (non-Javadoc)
     * 
     * @see
     * org.apache.jmeter.report.csv.processor.SamplePredicate#matches(org.apache
     * .jmeter.report.csv.core.Sample)
     */
    @Override
    public boolean matches(Sample sample) {
	if (sample == null) {
	    throw new ArgumentNullException("sample");
	}

	return sample.isController();
    }

}
