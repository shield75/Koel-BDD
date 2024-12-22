Feature: Update Email

  Scenario Outline: Update email with invalid email address
    Given The user is logged into the koel app using and is in the profile and preference page
    When Enter a new email address "<newEmail>" and save
    Then The mail "<newEmail>" should not be updated and validated with "<expectedMessage>"

    Examples:
      | newEmail             | expectedMessage                                                                                    |
      | invalidtestpro.io    | Please include an '@' in the email address. 'invalidtestpro.io' is missing an '@'.                 |
      | rumneul@domain       | Please include a 'testpro.io' domain in the email address. 'rumneul@domain' is missing a 'domain'. |
      | rumenul@domain.com   | Please include a 'dot(.)' in the email address. 'rumenul@domain.co' is missing a '.'.              |
      | rumenul+@example.com | Please remove the (+) in the email address before '@'.                                             |
      | jason@testpro.io     | this user already exists.                                                                          |

  Scenario Outline: Update email with a valid email address
    Given The user is logged into the koel app using and is in the profile and preference page
    When Enter a new email address "<newEmail>" and save
    Then the mail should be updated and message "<expectedMessage>" should be displayed

    Examples:
      | newEmail               | expectedMessage  |
      | valid.email@testpro.io | Profile updated. |