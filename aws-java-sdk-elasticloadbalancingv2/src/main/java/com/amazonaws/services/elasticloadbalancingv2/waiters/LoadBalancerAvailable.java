/*
 * Copyright 2012-2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance with
 * the License. A copy of the License is located at
 * 
 * http://aws.amazon.com/apache2.0
 * 
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */

package com.amazonaws.services.elasticloadbalancingv2.waiters;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.annotation.SdkInternalApi;
import com.amazonaws.waiters.WaiterAcceptor;
import com.amazonaws.waiters.WaiterState;
import com.amazonaws.waiters.AcceptorPathMatcher;
import com.amazonaws.services.elasticloadbalancingv2.model.*;

import com.fasterxml.jackson.databind.JsonNode;

import com.amazonaws.jmespath.*;

import java.io.IOException;
import javax.annotation.Generated;

@SdkInternalApi
@Generated("com.amazonaws:aws-java-sdk-code-generator")
class LoadBalancerAvailable {

    static class IsActiveMatcher extends WaiterAcceptor<DescribeLoadBalancersResult> {
        private static final JsonNode expectedResult;

        static {
            try {
                expectedResult = ObjectMapperSingleton.getObjectMapper().readTree("\"active\"");
            } catch (IOException ioe) {
                throw new RuntimeException(ioe);
            }
        }

        private static final JmesPathExpression ast = new JmesPathProjection(new JmesPathFlatten(new JmesPathField("LoadBalancers")),
                new JmesPathSubExpression(new JmesPathField("State"), new JmesPathField("Code")));

        /**
         * Takes the result and determines whether the state of the resource matches the expected state. To determine
         * the current state of the resource, JmesPath expression is evaluated and compared against the expected result.
         * 
         * @param result
         *        Corresponding result of the operation
         * @return True if current state of the resource matches the expected state, False otherwise
         */
        @Override
        public boolean matches(DescribeLoadBalancersResult result) {
            JsonNode queryNode = ObjectMapperSingleton.getObjectMapper().valueToTree(result);
            JsonNode finalResult = ast.accept(new JmesPathEvaluationVisitor(), queryNode);
            return AcceptorPathMatcher.pathAll(expectedResult, finalResult);
        }

        /**
         * Represents the current waiter state in the case where resource state matches the expected state
         * 
         * @return Corresponding state of the waiter
         */
        @Override
        public WaiterState getState() {
            return WaiterState.SUCCESS;
        }
    }

    static class IsProvisioningMatcher extends WaiterAcceptor<DescribeLoadBalancersResult> {
        private static final JsonNode expectedResult;

        static {
            try {
                expectedResult = ObjectMapperSingleton.getObjectMapper().readTree("\"provisioning\"");
            } catch (IOException ioe) {
                throw new RuntimeException(ioe);
            }
        }

        private static final JmesPathExpression ast = new JmesPathProjection(new JmesPathFlatten(new JmesPathField("LoadBalancers")),
                new JmesPathSubExpression(new JmesPathField("State"), new JmesPathField("Code")));

        /**
         * Takes the result and determines whether the state of the resource matches the expected state. To determine
         * the current state of the resource, JmesPath expression is evaluated and compared against the expected result.
         * 
         * @param result
         *        Corresponding result of the operation
         * @return True if current state of the resource matches the expected state, False otherwise
         */
        @Override
        public boolean matches(DescribeLoadBalancersResult result) {
            JsonNode queryNode = ObjectMapperSingleton.getObjectMapper().valueToTree(result);
            JsonNode finalResult = ast.accept(new JmesPathEvaluationVisitor(), queryNode);
            return AcceptorPathMatcher.pathAny(expectedResult, finalResult);
        }

        /**
         * Represents the current waiter state in the case where resource state matches the expected state
         * 
         * @return Corresponding state of the waiter
         */
        @Override
        public WaiterState getState() {
            return WaiterState.RETRY;
        }
    }

    static class IsLoadBalancerNotFoundMatcher extends WaiterAcceptor<DescribeLoadBalancersResult> {
        /**
         * Takes the response exception and determines whether this exception matches the expected exception, by
         * comparing the respective error codes.
         * 
         * @param e
         *        Response Exception
         * @return True if it matches, False otherwise
         */
        @Override
        public boolean matches(AmazonServiceException e) {
            return "LoadBalancerNotFound".equals(e.getErrorCode());
        }

        /**
         * Represents the current waiter state in the case where resource state matches the expected state
         * 
         * @return Corresponding state of the waiter
         */
        @Override
        public WaiterState getState() {
            return WaiterState.RETRY;
        }
    }
}
