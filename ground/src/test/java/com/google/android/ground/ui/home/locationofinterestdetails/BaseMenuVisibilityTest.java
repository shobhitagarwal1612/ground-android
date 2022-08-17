/*
 * Copyright 2021 Google LLC
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

package com.google.android.ground.ui.home.locationofinterestdetails;

import com.google.android.ground.BaseHiltTest;
import com.google.android.ground.model.AuditInfo;
import com.google.android.ground.model.Survey;
import com.google.android.ground.model.User;
import com.google.android.ground.model.locationofinterest.LocationOfInterest;
import com.google.android.ground.test.FakeData;
import com.google.android.ground.test.system.auth.FakeAuthenticationManager;
import com.google.common.collect.ImmutableMap;
import javax.inject.Inject;

public abstract class BaseMenuVisibilityTest extends BaseHiltTest {

  static final User TEST_USER_OWNER = FakeData.USER.toBuilder().setEmail("user1@gmail.com").build();

  static final User TEST_USER_MANAGER =
      FakeData.USER.toBuilder().setEmail("user2@gmail.com").build();

  static final User TEST_USER_CONTRIBUTOR =
      FakeData.USER.toBuilder().setEmail("user3@gmail.com").build();

  static final User TEST_USER_UNKNOWN =
      FakeData.USER.toBuilder().setEmail("user4@gmail.com").build();

  private static final Survey TEST_SURVEY =
      FakeData.SURVEY.toBuilder()
          .setAcl(
              ImmutableMap.<String, String>builder()
                  .put(TEST_USER_OWNER.getEmail(), "owner")
                  .put(TEST_USER_MANAGER.getEmail(), "survey_organizer")
                  .put(TEST_USER_CONTRIBUTOR.getEmail(), "data_collector")
                  .build())
          .build();

  protected final User user;
  protected final LocationOfInterest locationOfInterest;
  protected final boolean visible;

  @Inject FakeAuthenticationManager fakeAuthenticationManager;
  @Inject LocationOfInterestDetailsViewModel viewModel;

  public BaseMenuVisibilityTest(User user, LocationOfInterest locationOfInterest, boolean visible) {
    this.user = user;
    this.locationOfInterest = locationOfInterest;
    this.visible = visible;
  }

  static LocationOfInterest createPointOfInterest(User user) {
    return new LocationOfInterest(
        FakeData.POINT_OF_INTEREST.getId(),
        TEST_SURVEY,
        FakeData.POINT_OF_INTEREST.getJob(),
        FakeData.POINT_OF_INTEREST.getCustomId(),
        FakeData.POINT_OF_INTEREST.getCaption(),
        AuditInfo.now(user),
        FakeData.POINT_OF_INTEREST.getLastModified(),
        FakeData.POINT_OF_INTEREST.getGeometry());
  }

  static LocationOfInterest createAreaOfInterest(User user) {
    return new LocationOfInterest(
        FakeData.AREA_OF_INTEREST.getId(),
        TEST_SURVEY,
        FakeData.AREA_OF_INTEREST.getJob(),
        FakeData.AREA_OF_INTEREST.getCustomId(),
        FakeData.AREA_OF_INTEREST.getCaption(),
        AuditInfo.now(user),
        FakeData.AREA_OF_INTEREST.getLastModified(),
        FakeData.AREA_OF_INTEREST.getGeometry());
  }
}
