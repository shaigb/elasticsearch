/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.elasticsearch.rest.plugins;

import org.elasticsearch.common.settings.Setting;
import org.elasticsearch.plugins.ActionPlugin;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.rest.RestHandler;
import org.elasticsearch.search.SearchModule;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Adds {@link TestDeprecationHeaderRestAction} for testing deprecation requests via HTTP.
 */
public class TestDeprecationPlugin extends Plugin implements ActionPlugin {

    @Override
    public List<Class<? extends RestHandler>> getRestHandlers() {
        return Collections.singletonList(TestDeprecationHeaderRestAction.class);
    }

    @Override
    public List<Setting<?>> getSettings() {
        return Arrays.asList(
            TestDeprecationHeaderRestAction.TEST_DEPRECATED_SETTING_TRUE1,
            TestDeprecationHeaderRestAction.TEST_DEPRECATED_SETTING_TRUE2,
            TestDeprecationHeaderRestAction.TEST_NOT_DEPRECATED_SETTING);
    }

    public void onModule(SearchModule module) {
        module.registerQuery(TestDeprecatedQueryBuilder::new,
                             TestDeprecatedQueryBuilder::fromXContent,
                             TestDeprecatedQueryBuilder.QUERY_NAME_FIELD);
    }

}
