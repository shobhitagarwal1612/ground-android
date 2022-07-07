/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.ground.persistence.remote.firestore.schema;

import com.google.android.ground.model.task.Step;

/**
 * Converts between Firestore nested objects and {@link Step} instances.
 */
class ElementConverter {

  static Step toStep(String id, ElementNestedObject em) {
    return FieldConverter.toField(id, em).map(Step::ofField).orElse(Step.ofUnknown());
  }
}