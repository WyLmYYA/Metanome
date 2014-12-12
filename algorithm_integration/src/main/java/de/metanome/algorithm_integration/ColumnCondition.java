/*
 * Copyright 2014 by the Metanome project
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

package de.metanome.algorithm_integration;

import com.google.gwt.user.client.rpc.IsSerializable;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * Abstract super class for the composite pattern used for column conditions.
 *
 * @author Jens Ehrlich
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type")
@JsonSubTypes({
                  @JsonSubTypes.Type(value = ColumnConditionAnd.class, name = "columnConditionAnd"),
                  @JsonSubTypes.Type(value = ColumnConditionOr.class, name = "columnConditionOr"),
                  @JsonSubTypes.Type(value = ColumnConditionValue.class, name = "columnConditionValue")
              })
public interface ColumnCondition extends IsSerializable, Comparable<ColumnCondition> {

  public static final String OPEN_BRACKET = "[";
  public static final String CLOSE_BRACKET = "]";
  public static final String AND = "^";
  public static final String OR = "v";
  public static final String NOT = "\u00AC";


  /**
   * Returns the condition as {@link java.lang.String}.
   *
   * @return condition
   */
  public String toString();

  /**
   * Adds a child to the called {@link de.metanome.algorithm_integration.ColumnCondition}.
   *
   * @param value child to by added
   * @return this
   */
  public ColumnCondition add(ColumnCondition value);

  /**
   * Calculates the coverage of all contained {@link de.metanome.algorithm_integration.ColumnCondition}s,
   * when the coverage is set on the children. The coverage is only correct if there are no
   * overlapping OR conditions.
   *
   * @return coverage between 0 and 100
   */
  public float getCoverage();

  /**
   * Returns all {@link de.metanome.algorithm_integration.ColumnIdentifier} that occur in the condition.
   *
   * @return sorted set of contained {@link de.metanome.algorithm_integration.ColumnIdentifier}s
   */
  public TreeSet<ColumnIdentifier> getContainedColumns();

  /**
   * Traverses all children and builds a pattern tableau, which represents the condition. Each element in the list represents a OR condition and each entry in the map a AND condition. The pattern tableau is only correct if the {@link de.metanome.algorithm_integration.ColumnCondition} is in disjunctive normal form.
   *
   * @return pattern tableau
   */
  public List<Map<ColumnIdentifier, String>> getPatternConditions();
}
