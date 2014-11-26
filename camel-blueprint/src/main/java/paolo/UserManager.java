/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package paolo;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pires.example.dal.UserService;
import com.github.pires.example.dal.entities.User;

public class UserManager {

  private UserService userService;

  private static final Logger log = LoggerFactory.getLogger(UserManager.class);

  public List<User> listUsers() {
    log.info("Listing users...");
    return userService.findAll();
  }

  public int countUsers() {
    log.info("Counting users...");
    return userService.count();
  }

  public void createUser(final User user) {
    log.info("Creating user with name {}...", user.getName());
    userService.create(user);
  }


  public void createDummyUser() {
    log.info("Creating Dummy user...");
    User user = new User();
    user.setName("user_name_" + System.currentTimeMillis());
    userService.create(user);
  }

  public UserService getUserService() {
    return userService;
  }

  public void setUserService(UserService userService) {
    this.userService = userService;
  }

}