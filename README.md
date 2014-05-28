# Tinnitus Masker

The goal of this app is to generate real-time audio to reduce the effects of
Tinnitus.  Depending on the person, different sounds may help or not help at
all.  By making this app as configurable as possible, I hope to help more
people


## Implemented Features

- **White Noise**. Simply uses a random number generator to choose amplitudes.
  This has an equal power distribution.
- **Brown Noise**. Implements Brownian motion via a [random
  walk](http://en.wikipedia.org/wiki/Random_walk).

## In the Works

- **Rain Sounds**.  Lots of rain sound apps and websites actually use a
  pre-recorded loop. It can be jarring to the user when the loop restarts. I
  aim to solve this problem by generating rain sounds in real time. This might
  be hard! 
- **Tinnitus Matching**.  This will be a tool to let the user determine which
  frequency their Tinnitus is centered on.  It will use a series of
  higher-or-lower questions within the hearing frequency range with a series of
  questions to determine the frequency. For example, the computer might play a
  500Hz sine wave and ask the user if their Tinnitus is higher or lower than
  that.
