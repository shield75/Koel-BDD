Feature: Update Email

  Scenario Outline: Update email with invalid email address
    Given The user is logged into the koel app using and is in the profile and preference page
    When Enter a new email address "<newEmail>" and save
    Then the mail should be validated with "<expectedMessage>"

    Examples:
       |newEmail | expectedMessage |
       | invalidtestpro.io | Please include an '@' in the email address. 'invalidtestpro.io' is missing an '@'. |
