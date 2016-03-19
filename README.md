# Road Runner
<center>
<img src="./art/roadrunnerlogo.png " alt="Drawing" style="width: 300px;" />
</center>
</br>
<p>
Road Runner is a library for android with with you can make your own loading
animation using SVG image
<p/>


## Sample video

## Screnshots

asd

#### Road Runner intro
<center>
<img src="./art/roadRunner.gif " alt="Drawing" style="width: 250px;" />


#### Determiante
<center>
<img src="./art/cityshort.gif " alt="Drawing" style="width: 250px;" />
<img src="./art/citylong.gif " alt="Drawing" style="width: 250px;" />

#### Determiante with value update
<center>
<img src="./art/determinateTwoWay.gif " alt="Drawing" style="width: 250px;" />

#### Material animation with twitter logo
<center>
<img src="./art/materialTwitter.gif " alt="Drawing" style="width: 250px;" />

#### TwoWay animation with Github logo
<center>
<img src="./art/twowaygthub.gif " alt="Drawing" style="width: 250px;" />

## How to

The library use the standard String path information (only one path) and the original size to works, you needs to obtain it using a external tool, the path information look like this:

```
M306.314,58.9801 C275.235,27.9011,224.837,27.9011,193.759,58.9801
L39.0019,213.736 C15.6832,237.055,15.6832,274.838,39.0019,298.158
C58.2219,317.378,87.2116,320.482,109.874,308.007
C112.241,307.888,114.569,306.993,116.38,305.202 L271.136,150.445
C286.675,134.906,286.675,109.717,271.136,94.1779
C255.597,78.6389,230.408,78.6389,214.869,94.1779 L88.2461,220.8
C84.366,224.68,84.366,230.987,88.2461,234.866
C92.1263,238.746,98.4335,238.746,102.313,234.866 L228.935,108.245
C236.715,100.465,249.309,100.465,257.07,108.245
C264.85,116.025,264.85,128.619,257.07,136.379 L109.337,284.111
C93.7979,299.65,68.6085,299.65,53.0694,284.111
C37.5304,268.572,37.5304,243.383,53.0694,227.844 L207.825,73.0468
C231.144,49.7281,268.928,49.7281,292.247,73.0468
C315.566,96.3654,315.566,134.149,292.247,157.469 L151.558,298.158
C147.678,302.038,147.678,308.345,151.558,312.225
C155.438,316.105,161.745,316.105,165.625,312.225 L306.314,171.535
C337.393,140.457,337.393,90.0591,306.314,58.98 Z
```
And the with and hegiht can be found in the svg definition:

```
height="316"
width="512"
```


### Using the view (Samples)

#### Two way

``` xml
<com.github.glomadrian.roadrunner.IndeterminateRoadRunner
      android:id="@+id/two_way"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      lib:movement_loop_time="4000"
      lib:movement_direction="counter_clockwise"
      lib:path_color="@color/two_way"
      lib:line_size="0.05"
      lib:stroke_width="3sp"
      lib:left_line_animation_time="2800"
      lib:left_line_max_size="0.5"
      lib:left_line_animation_start_delay="2500"
      lib:right_line_animation_start_delay="2000"
      lib:right_line_max_size="0.5"
      lib:right_line_animation_time="2000"
      lib:path_data="@string/github"
      lib:path_original_width="@integer/github_original_width"
      lib:path_original_height="@integer/github_original_height"
      lib:path_animation_type="twoWay"
      />
```
#### Material
``` xml
<com.github.glomadrian.roadrunner.IndeterminateRoadRunner
     android:id="@+id/material"
     android:layout_width="match_parent"
     android:layout_height="match_parent"
     lib:movement_direction="counter_clockwise"
     lib:path_color="#FFFFFF"
     lib:stroke_width="3sp"
     lib:path_data="@string/twitter"
     lib:path_original_width="@integer/twitter_original_width"
     lib:path_original_height="@integer/twitter_original_height"
     lib:path_animation_type="material"
     />
```
#### Determinate To way
``` xml
<com.github.glomadrian.roadrunner.DeterminateRoadRunner
    android:id="@+id/determinate"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    lib:min="0"
    lib:max="100"
    lib:movement_direction="counter_clockwise"
    lib:path_color="@color/colorAccent"
    lib:stroke_width="2sp"
    lib:movement_loop_time="1500"
    lib:path_data="@string/clip"
    lib:path_original_width="@integer/clip_original_width"
    lib:path_original_height="@integer/clip_original_height"
    lib:animate_on_start="false"
    />
```

## Custom attributes

### Common

* <font color="#FF4B4A">movement_direction:</font> clockwise or counter_clockwise
* <font color="#FF4B4A">path_color: color of the painted path
* <font color="#FF4B4A">stroke_width: width of the painted path
* <font color="#FF4B4A">path_data:</font> String with the path information
* <font color="#FF4B4A">path_original_width:</font> The original with defined in the SVG
* <font color="#FF4B4A">path_original_height:</font> The original height defined in the SVG
* <font color="#FF4B4A">animate_on_start:</font> true or false, init the animation on first paint (true by default)


### Indeterminate
* <font color="#FF4B4A">path_animation_type:</font> Select indetermina animation type, can be:
  * material
  * twoWay

#### Indeterminate Material

* Dont have any custom attributes

#### Indeterminate Two way
* <font color="#FF4B4A">movement_loop_time:</font> Time take to do a complete loop
* <font color="#FF4B4A">line_size:</font><font color="#625195">\*</font> The size of the base line
* <font color="#FF4B4A">left_line_animation_time:</font> Time take to do a complete animation to the left line
* <font color="#FF4B4A">right_line_animation_time:</font> Time take to do a complete animation to the right line
* <font color="#FF4B4A">left_line_max_size:</font><font color="#625195">\*</font>  The max size that the left line can research in the animation
* <font color="#FF4B4A">right_line_max_size:</font><font color="#625195">\*</font>  The max size that the right line can research in the animation
* <font color="#FF4B4A">left_line_animation_start_delay:</font> Time to wait to start the left line animation (in milliseconds)
* <font color="#FF4B4A">right_line_animation_start_delay:</font> Time to wait to start the right line animation (in milliseconds)

\* <font color="#625195">From 0f to 1f, 1f is all the path</font>

### Determinate
* <font color="#FF4B4A">min:</font> Min value for the progress
* <font color="#FF4B4A">max:</font> Max value for the progress
* <font color="#FF4B4A">movement_loop_time:</font> Time take to do a complete loop
* <font color="#FF4B4A">movement_line_size:</font><font color="#625195">\*</font>  The size of the line

\* <font color="#625195">From 0f to 1f, 1f is all the path</font>


## Attributions
- rommannurik
- jorge
