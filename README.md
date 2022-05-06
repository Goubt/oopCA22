# Music Visualiser Project

Names: Aleksey Makarevich, Finn Maguire, Yaroslav Hrabas

Student Numbers: C20402732, C20492576, C20394791

Songs: Poison, Gummi Bear Song, DARE

# Description of the assignment
Our assignment consists of a rotating menu. This was done as a challenge, seeing if we could utilise the camera to move around within the 3d space.
The user "stands" in the centre of the menu, able to turn the view point between the three visualations while it changes the song to match the visualisation.

## Aleksey

## Yaris

## Finn
For my visualiser I wanted to try and utilise some kind of fractals. I decided to use tree fractals generated circularly around a point which would alter themselves
in amplitude and rotation depending on the sound levels from the audio. I included some forms of interactivity with the visualiser which allows the user to change the 
amount of branches and a modifier for the trees amplitude which allows for some interesting and psychedelic patterns. The visual also provides function to manually rotate following the users mouse instead of its preset rotation. Some other features include some display options and switching rotation directions. 

# Instructions
Each menu option has its own controls that can manipulate features in each visualations.

## The Global Controls
- "Right arrow" rotates the menu one slot to the right.
- "Left arrow" rotates the menu one slow to the left.
- "Space bar" to pause / play the music.
- "B" to turn on/off background beat brightness.

## Poison controls
- Mouse controls direction the poison bubbles move in.
- "L" to lock/unlock camera.
With camera locked you can now
- "K" to move back from the visual.
- "I" to move in towards the visual.

## Dare controls
- "X"
- "C"
- "H"
- "Up Arrow"
- "Down Arrow"

# How it works

## Aleksey 


## Yaris


## Finn

### FractalTree :

This java class is used to create and display tree fractals circularly around a point.
The class's constructor reads in variables which determines the amplitude, angles, number of branches and the amount of trees for the visual.
![image](https://user-images.githubusercontent.com/98547854/167218700-7e1b7793-347b-4853-99d0-ebf494c54ed5.png)
The constructor also calls the branch method which creates one fractal tree.

Inside the method the angles for the branches are determined by the audios averaged amplitude using PApplets map function. 
The method utlises recursion to create branches which are connected to its parent branch using arrays.
![image](https://user-images.githubusercontent.com/98547854/167220463-66aecbf1-7463-4432-80ff-7046fe7b547b.png)


The tree is displayed on screen by calling the render() method which inside calls the display() method which draws each individual tree.
Inside render() is where the position of the trees are determined using rotate() and translate().  
![image](https://user-images.githubusercontent.com/98547854/167220492-da39f0ff-4653-404e-bfe4-7ced60905c40.png)
The visual is then either spun using a default method or using the users cursor.

### Colours & DynamicColours

These 2 classes serve as a way to provide smooth colour shifting to drawn objects.
The colours class is used to create variables that will be used in the DynamicColours class.
This is done to prevent the same variables being manipulated if the DynamicColours method is being used more than once at a time which in turn would increase the 
rate at which the colours would chance for every instance.
RGB values are assigned at random which can allow for a different colour scheme for every use of the method. 

![image](https://user-images.githubusercontent.com/98547854/167221071-7c04d8e5-4daa-4a50-a47f-c75ac934e788.png)






# What we are most proud of in the assignment
### Aleksey 

## Yaris

### Finn



# Markdown Tutorial

This is *emphasis*

This is a bulleted list

- Item
- Item

This is a numbered list

1. Item
1. Item

This is a [hyperlink](http://bryanduggan.org)

# Headings
## Headings
#### Headings
##### Headings

This is code:

```Java
public void render()
{
	ui.noFill();
	ui.stroke(255);
	ui.rect(x, y, width, height);
	ui.textAlign(PApplet.CENTER, PApplet.CENTER);
	ui.text(text, x + width * 0.5f, y + height * 0.5f);
}
```

So is this without specifying the language:

```
public void render()
{
	ui.noFill();
	ui.stroke(255);
	ui.rect(x, y, width, height);
	ui.textAlign(PApplet.CENTER, PApplet.CENTER);
	ui.text(text, x + width * 0.5f, y + height * 0.5f);
}
```

This is an image using a relative URL:

![An image](images/p8.png)

This is an image using an absolute URL:

![A different image](https://bryanduggandotorg.files.wordpress.com/2019/02/infinite-forms-00045.png?w=595&h=&zoom=2)

This is a youtube video:

[![YouTube](http://img.youtube.com/vi/J2kHSSFA4NU/0.jpg)](https://www.youtube.com/watch?v=J2kHSSFA4NU)

This is a table:

| Heading 1 | Heading 2 |
|-----------|-----------|
|Some stuff | Some more stuff in this column |
|Some stuff | Some more stuff in this column |
|Some stuff | Some more stuff in this column |
|Some stuff | Some more stuff in this column |
