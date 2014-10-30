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

package de.metanome.frontend.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.metanome.backend.results_db.TableInput;

import java.util.List;

public interface TableInputServiceAsync {

  void listTableInputs(AsyncCallback<List<TableInput>> async);

  void getTableInput(long id, AsyncCallback<TableInput> async);

  void storeTableInput(TableInput input, AsyncCallback<TableInput> async);

  void deleteTableInput(TableInput input, AsyncCallback<Void> async);
}
