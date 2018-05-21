/*-
 * -\-\-
 * Flo Styx
 * --
 * Copyright (C) 2016 - 2018 Spotify AB
 * --
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
 * -/-/-
 */

package com.spotify.flo.contrib.styx;

import com.spotify.flo.contrib.dataflow.PipelineOptionsProvider;
import io.norberg.automatter.AutoMatter;
import java.util.Optional;
import org.apache.beam.runners.dataflow.options.DataflowPipelineOptions;

@AutoMatter
public interface LabeledPipelineOptionsProvider {

  String DEFAULT_LABEL_PREFIX = "spotify";

  String STYX_COMPONENT_ID_LABEL = "styx-component-id";
  String STYX_WORKFLOW_ID_LABEL = "styx-workflow-id";
  String STYX_PARAMETER_LABEL = "styx-parameter";
  String STYX_EXECUTION_ID_LABEL = "styx-execution-id";
  String STYX_TRIGGER_ID_LABEL = "styx-trigger-id";
  String STYX_TRIGGER_TYPE_LABEL = "styx-trigger-type";

  Optional<String> labelPrefix();
  
  PipelineOptionsProvider pipelineOptionsProvider();

  static LabeledPipelineOptionsProviderBuilder builder() {
    return new LabeledPipelineOptionsProviderBuilder().labelPrefix(DEFAULT_LABEL_PREFIX);
  }
  
  default DataflowPipelineOptions options() {
    final DataflowPipelineOptions pipelineOptions = pipelineOptionsProvider().options();
    // TODO: add labels
    pipelineOptions.setLabels(null);
    return pipelineOptions;
  }

//  @Override
//  default PipelineOptions options() {
//    DataflowPipelineOptions pipelineOptions = PipelineOptionsProvider.super.options()
//        .as(DataflowPipelineOptions.class);
//
//    pipelineOptions.setLabels(getLabelsMap());
//    return pipelineOptions;
//  }
//
//  default Map<String,String> getLabelsMap(){
//    final Config config = ConfigFactory.load();
//
//    final Map<String, String> labels = new HashMap<>();
//
//    putLabel(labels, STYX_COMPONENT_ID_LABEL, config.getString(Constants.STYX_COMPONENT_ID));
//
////    labels.put(STYX_COMPONENT_ID_LABEL, config.getString(Constants.STYX_COMPONENT_ID));
////    labels.put(STYX_WORKFLOW_ID_LABEL, config.getString(Constants.STYX_WORKFLOW_ID));
////    labels.put(STYX_PARAMETER_LABEL, config.getString(Constants.STYX_PARAMETER));
////    labels.put(LABEL_PREFIX+STYX_EXECUTION_ID_LABEL, config.getString(Constants.STYX_EXECUTION_ID));
//  }
//
//  default void putLabel(Map<String, String> labels, String label, String value) {
//    //sanitize
//    //validate
////    labels.put(LABEL_PREFIX + label, value);
//  }
//
//  default String sanitizeLabelValue(String pad, String value) {
//    return "new-value";
//  }

}