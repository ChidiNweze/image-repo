# image-repo

Using Spring Boot and Postgres, I created simple backend infrastructure for an image repository.

This project consists of endpoints that allow the front end to:
- Create and save to the database a single image (including title, description, and list of tags)
- Create and save to the dataabse multiple images at once in a "photo dump"
- Update the description of an image
- Get all images
- Delete an image
- Search by a tag
- Search by multiple tags

I also included (some) tests of the functionality of the endpooints using MockMvc. 
All endpoints and their responses (success and exceptions) were tested online during development using Reqbin, a free online API testing tool.

The project can be run locally on one's computer. The postgres database named "image" may need to be created (and permissions granted to user) first before running the project.
