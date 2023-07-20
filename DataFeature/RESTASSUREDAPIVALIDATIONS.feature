Feature: Feature to test APIs


  #@apiTest @single_user
  #Scenario Outline: Verify single user APIs
    #Given User set <endPoint> service endpoint
    #When User perform <request> request type for <apiName>
    #And User verifies the response code for <apiName>
    #And User verifies the request path for <apiName>
    #And User verifies the response data for <apiName>
#
    #Examples:
      #| endPoint       | request | apiName       	 |
      #| "/api/users/2" | "GET"   | "SINGLE_USER"	 |
#
  #@apiTest @register_user
  #Scenario Outline: Verify register user APIs
    #Given User set <endPoint> service endpoint
    #When User perform <request> request type for <apiName>
    #And User verifies the response code for <apiName>
    #And User verifies the request path for <apiName>
    #And User verifies the response data for <apiName>
#
    #Examples:
      #| endPoint        | request | apiName         |
      #| "/api/register" | "POST"  | "REGISTER_USER" |

  @apiTest @create_user
  Scenario Outline: Verify create user api
    Given User set <endPoint> service endpoint
    When User perform <request> request type for <apiName>
    And User verifies the response code for <apiName>
    And User verifies the request path for <apiName>
    And User verifies the response data for <apiName>

    Examples:
      | endPoint     | request | apiName  |
      | "/api/users" | "POST"  | "CREATE" |


  #@apiTest @update_user
  #Scenario Outline: Verify update user api
    #Given User set <endPoint> service endpoint
    #When User perform <request> request type for <apiName>
    #And User verifies the response code for <apiName>
    #And User verifies the request path for <apiName>
    #And User verifies the response data for <apiName>
#
    #Examples:
      #| endPoint       | request | apiName       |
      #| "/api/users/2" | "PUT"   | "UPDATE_USER" |
#
  #@apiTest @patch_user
  #Scenario Outline: Verify patch user api
    #Given User set <endPoint> service endpoint
    #When User perform <request> request type for <apiName>
    #And User verifies the response code for <apiName>
    #And User verifies the request path for <apiName>
    #And User verifies the response data for <apiName>
#
    #Examples:
      #| endPoint       | request | apiName        |
      #| "/api/users/2" | "PATCH" | "PATCH_UPDATE" |
#
  #@apiTest @patch_user
  #Scenario Outline: Verify delete user api
    #Given User set <endPoint> service endpoint
    #When User perform <request> request type for <apiName>
    #And User verifies the response code for <apiName>
    #And User verifies the request path for <apiName>
#
    #Examples:
      #| endPoint       | request  | apiName       |
      #| "/api/users/2" | "DELETE" | "DELETE_USER" |
