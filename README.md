# Assignment 02. CodeViz 2.0


## Class Diagram

**[Lucid Chart Diagram](https://lucid.app/lucidchart/b5262268-9a3d-4d7e-9e72-029d0d42ca75/edit?viewport_loc=-1291%2C-799%2C3485%2C2698%2C0_0&invitationId=inv_10fffdfd-e760-4d75-a3a8-b084c06b8dcf)**


## Input 

**public GitHub repo**


## Output 

**Analyzes** all `.java` files found in given GitHub repo folder URL
- **visualizes** each `.java` file as a square in a grid, that encodes:
  - **color** --> code complexity (number of control statements)
    - Green for (<= 5)
    - Yellow for (6-10)
    - Red for (> 10)
  - **transparency** --> file size (number of non-empty lines)


## Run Instructions 
**1. Clone projecct**

**2. Environment Setup**
a. create a .env file in the root of the project directory
b. include your GitHub API token
```GH_TOKEN=<your_GH_token>```

**3. Reload/ Sync Maven Project** 

**4. Run Main.java** 


## Usage

**1. Enter GitHub folder URL in top bar**
```https://github.com/<user>/<repo>/tree/<ref>/...```

**2. press the OK button or File>Open from URL...**

**3. each .java file will be represented as a square in the grid** 
- hover over a square --> to see filename, size, complexity metrics
- click on a square --> to see filename in bottom bar

