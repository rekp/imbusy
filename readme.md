# imbusy
Personal side project.

Rough idea of what the app will do:
Auto response app that listens for a keyphrase and/or password on incoming SMS and 
returns location details or a set response. Further implementations will include the ability for auto responses to be chosen
based on other parameters, such as being connected to your car's bluetooth, or work's wifi.

Example: Jill texts Mike #whereyouat password and receives an automatic response on Mike's location.

Most recent issues include:
Working on a mechanisim to allow two individuals running the app to not fall into an infinite auto response loop.
  Ideas: 
    - Auto response once every x seconds.
    - Store last sent and check before sending next response
    - Implement inbox and check history (best in the long run as it allows for more features)

Project is in a very early stage.
