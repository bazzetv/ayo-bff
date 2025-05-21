TRUNCATE TABLE exercise;

INSERT INTO program (
    title,
    description,
    duration_weeks,
    days_per_week,
    level,
    category,
    goal,
    coach_name,
    structure,
    image_url,
    tags,
    is_published
) VALUES (
    'Upper/Lower Hypertrophy',
    'An intense bodybuilding 5 days / week program',
    10,
    5,
    'INTERMEDIATE',
    'BODYBUILDING',
    'Pure muscle gain',
    'Jeff Nippard',
    '[
       {
         "week": 1,
         "days": [
           {
             "name": "Upper #1",
             "exercises": [
               {
                 "name": "Cuffed Behind-The-Back Lateral Raise",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Cross-Body Cable Y-Raise",
                   "DB Lateral Raise"
                 ],
                 "notes": "Raise the cables up and out in a \"Y\" motion. Really try to connect with the middle delt fibers as you sweep the weight up and out."
               },
               {
                 "name": "Cross-Body Lat Pull-Around",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Half-Kneeling 1-Arm Lat Pulldown",
                   "Neutral-Grip Pullup"
                 ],
                 "notes": "Try to keep the cable and your wrist aligned in a straight line throughout the pull. Feel a nice, deep lat stretch at the top."
               },
               {
                 "name": "Low Incline Smith Machine Press",
                 "equipment": "MACHINE",
                 "sets": 4,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Low Incline Machine Press",
                   "Low Incline DB Press"
                 ],
                 "notes": "Set the bench at a ~15\u00b0 incline. 1 second pause on the chest on each rep while maintaining tension on the pecs."
               },
               {
                 "name": "Chest-Supported Machine Row",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Chest-Supported T-Bar Row",
                   "Helms Row"
                 ],
                 "notes": "Flare elbows out at roughly 45\u00b0 and squeeze your shoulder blades together hard at the top of each rep."
               },
               {
                 "name": "Overhead Cable Triceps Extension (Bar)",
                 "equipment": "CABLE",
                 "sets": 2,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Overhead Cable Triceps Extension (Rope)",
                   "DB Skull Crusher"
                 ],
                 "notes": "Feel a nasty stretch on the triceps throughout the entire negative. Pause for 1 second in the stretch part of each rep."
               },
               {
                 "name": "Straight-Bar Lat Prayer",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 12,
                 "rest": 90,
                 "substitutions": [
                   "Machine Lat Pullover",
                   "DB Lat Pullover"
                 ],
                 "notes": "Lean forward to get a big stretch on the lats at the top of the ROM and then stand upright as you squeeze your lats at the bottom."
               },
               {
                 "name": "Pec Deck (w/ Integrated Partials)",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 12,
                 "rest": 90,
                 "substitutions": [
                   "Bent-Over Cable Pec Flye (w/ Integrated Partials)",
                   "DB Flye (w/ Integrated Partials)"
                 ],
                 "notes": "Set up the pec deck to allow for maximum stretch. On all sets, alternate full-ROM reps and half-ROM reps (i.e. do 1 rep with full-ROM, then 1 rep half-ROM (in the stretched/bottom half), then 1 rep full-ROM, then 1 rep half-ROM). Repeat until you havee reached the target reps and an RPE of 9-10."
               }
             ]
           },
           {
             "name": "Lower #1",
             "exercises": [
               {
                 "name": "Seated Leg Curl",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Lying Leg Curl",
                   "Nordic Ham Curl"
                 ],
                 "notes": "Lean forward over the machine to get a maximum stretch in your hamstrings."
               },
               {
                 "name": "Machine Hip Adduction",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Cable Hip Adduction",
                   "Copenhagen Hip Adduction"
                 ],
                 "notes": "Mind-muscle connection with your inner thighs. These are great for adding thigh mass from the front! Push them hard!"
               },
               {
                 "name": "Hack Squat",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 6,
                 "rest": 180,
                 "substitutions": [
                   "Machine Squat",
                   "Front Squat"
                 ],
                 "notes": "We are using a reverse pyramid on this exercise. Warm-up as usual to your first working set for 4 reps. This first set will be your heaviest set. Then for set 2, drop the weight back ~10-15% and do 6 reps. Then for set 3, drop the weight back another 10-15% and do 8 reps."
               },
               {
                 "name": "Leg Extension",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "DB Step-Up",
                   "Reverse Nordic"
                 ],
                 "notes": "Set the seat back as far as it will go while still feeling comfortable. Grab the handles as hard as you can to pull your butt down into the seat. Use a 2-3 second negative. Feel your quads pulling apart on the negative."
               },
               {
                 "name": "Leg Press Calf Press",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 12,
                 "rest": 90,
                 "substitutions": [
                   "Donkey Calf Raise",
                   "Seated Calf Raise"
                 ],
                 "notes": "1-2 second pause at the bottom of each rep. Instead of just going up onto your toes, think about rolling your ankle back and forth on the balls of your feet."
               }
             ]
           },
           {
             "name": "Upper #2",
             "exercises": [
               {
                 "name": "Super-ROM Overhand Cable Row",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Overhand Machine Row",
                   "Arm-Out Single-Arm DB Row"
                 ],
                 "notes": "Set up a wide grip pulldown bar on a seated cable row. Using a double overhand grip, perform rows while leaning forward on the negative and then extend your torso to be upright as you finish the row."
               },
               {
                 "name": "Machine Shoulder Press",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Cable Shoulder Press",
                   "Seated DB Shoulder Press"
                 ],
                 "notes": "Ensure that your elbows break at least 90\u00b0. Mind-muscle connection with your delts. Smooth, controlled reps."
               },
               {
                 "name": "Assisted Pull-Up",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Lat Pulldown",
                   "Machine Pulldown"
                 ],
                 "notes": "Slow 2-3 second negative. Feel your lats pulling apart on the way down. Slight 0.5-1 second pause at the bottom, then lift your chest up and drive your elbows down as you lift yourself up. do not be afraid to use assistance. Keep the form tight and controlled!"
               },
               {
                 "name": "Paused Assisted Dip",
                 "equipment": "BODYWEIGHT",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Decline Machine Chest Press",
                   "Decline Barbell Press"
                 ],
                 "notes": "Slow 2-3 second negative. 1-2 second pause at the bottom. Explode with control on the way up. Go as deep as your shoulders comfortably allow, trying to at least break a 90\u00b0 elbow angle."
               },
               {
                 "name": "Inverse DB Zottman Curl",
                 "equipment": "DUMBBELL",
                 "sets": 2,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Slow-Eccentric DB Curl",
                   "Hammer Curl"
                 ],
                 "notes": "Do a hammer curl on the positive, then turn your palms facing up at the top and use a palms-up grip on the negative."
               },
               {
                 "name": "Super-ROM DB Lateral Raise",
                 "equipment": "DUMBBELL",
                 "sets": 3,
                 "reps": 12,
                 "rest": 0,
                 "substitutions": [
                   "Cable Upright Row",
                   "DB Lateral Raise"
                 ],
                 "notes": "Perform lateral raises as normal, except going until your hands are up overhead. As you break parallel, you will use more upper traps to move the weight. Feel free to squeeze your upper traps at the top. If you feel shoulder pain when going all the way up, try pointing your thumb up or simply stop at parallel and do normal lateral raises."
               },
               {
                 "name": "Cable Reverse Flye (Mechanical Dropset)",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 5,
                 "rest": 90,
                 "substitutions": [
                   "Reverse Pec Deck",
                   "Bent-Over Reverse DB Flye"
                 ],
                 "notes": "Youwill probably want to watch the video for this one. Take ~3 big steps back from the cable machine and do your first 5 reps. After those first 5 reps, immediately (without resting) take 1 step forward and do another 4 reps. Then (without resting) take another step forward and do at least another 3 reps (or until you hit RPE 9-10)."
               }
             ]
           },
           {
             "name": "Lower #2",
             "exercises": [
               {
                 "name": "Lying Leg Curl",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Seated Leg Curl",
                   "Nordic Ham Curl"
                 ],
                 "notes": "Set the machine so that you get the biggest stretch possible at the bottom. Prevent your butt from popping up as you curl. Once you get to the full squeeze, continue with partial reps on the last set."
               },
               {
                 "name": "Leg Press",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 8,
                 "rest": 90,
                 "substitutions": [
                   "Belt Squat",
                   "High-Bar Back Squat"
                 ],
                 "notes": "Feet lower on the platform for more quad focus. Get as deep as you can without excessive back rounding. Control the negative and do a slight pause at the bottom of each rep. Try to add a little weight each week at the same rep count."
               },
               {
                 "name": "Paused Barbell RDL",
                 "equipment": "BARBELL",
                 "sets": 2,
                 "reps": 8,
                 "rest": 180,
                 "substitutions": [
                   "Paused DB RDL",
                   "Glute-Ham Raise"
                 ],
                 "notes": "The RPE is intentionally low here because these will cause a lot of muscle damage. do not be tempted to go too heavy. 1 second pause at the bottom of each rep. To keep tension on the hamstrings, stop about 75% of the way to full lockout on each rep (i.e. stay in the bottom 3/4 of the range of motion)."
               },
               {
                 "name": "A1: Machine Hip Adduction",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 0,
                 "substitutions": [
                   "Cable Hip Adduction",
                   "Copenhagen Hip Adduction"
                 ],
                 "notes": "Mind-muscle connection with your inner thighs. These are great for adding thigh mass from the front! Push them hard!"
               },
               {
                 "name": "A2: Sissy Squat",
                 "equipment": "BODYWEIGHT",
                 "sets": 3,
                 "reps": 10,
                 "rest": 0,
                 "substitutions": [
                   "Leg Extension",
                   "Goblet Squat"
                 ],
                 "notes": "Allow yourself to come up onto your toes and push your knees forward past your toes. This is safe for the knees. If you feel knee pain doing them, though, feel free to go with a substitution. They may feel awkward at first, but they really underrated for the quads! do not give up on them too quickly."
               },
               {
                 "name": "Standing Calf Raise",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Leg Press Calf Press",
                   "Donkey Calf Raise"
                 ],
                 "notes": "1-2 second pause at the bottom of each rep. Instead of just going up onto your toes, think about rolling your ankle back and forth on the balls of your feet."
               }
             ]
           },
           {
             "name": "Arms & Weak Points",
             "exercises": [
               {
                 "name": "Incline Cable Lateral Raise",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 60,
                 "notes": "Decide on your weak point using The Weak Point Table in your Hypertrophy Handbook. Perform ONE of the exercises listed under Exercise 1 for the sets and reps provided here.",
                 "substitutions": [
                   "DB Lateral Raise",
                   "Machine Lateral Raise"
                 ]
               },
               {
                 "name": "Bayesian Cable Curl",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "DB Incline Curl",
                   "DB Scott Curl"
                 ],
                 "notes": "If you have a left-right bicep size imbalance, do these 1 arm at a time, starting with the weaker arm. Take the weaker arm to an RPE of 9-10. Then match the reps with the other arm (stop once you havee matched the reps, even if the RPE is lower). If you do not have a size imbalance, do these both arms at the same time."
               },
               {
                 "name": "Seated DB French Press",
                 "equipment": "DUMBBELL",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "EZ-bar Skull Crusher",
                   "DB Skull Crusher"
                 ],
                 "notes": "Place both palms under the head of a dumbbell and perform overhead extensions. Feel a deep stretch on your triceps at the bottom. Avoid pausing at the top of each rep."
               },
               {
                 "name": "Bottom-2/3 Constant Tension Preacher Curl",
                 "equipment": "OTHER",
                 "sets": 2,
                 "reps": 12,
                 "rest": 90,
                 "substitutions": [
                   "Bottom-2/3 EZ-Bar Curl",
                   "Spider Curl"
                 ],
                 "notes": "Stay in the bottom 2/3 of the curl. do not squeeze all the way up to the top. Keep your triceps firmly pinned against the pad as you curl. No pausing at the top or bottom: constant tension on the biceps!"
               },
               {
                 "name": "Cable Triceps Kickback",
                 "equipment": "CABLE",
                 "sets": 2,
                 "reps": 12,
                 "rest": 90,
                 "substitutions": [
                   "Bench Dip",
                   "DB Triceps Kickback"
                 ],
                 "notes": "There are two ways you can do this: upright or bent over. Choose the one that feels more comfortable for you. The main thing is that when you are in the full squeeze, your shoulder should be positioned back behind your torso."
               },
               {
                 "name": "Cable Crunch",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Machine Crunch",
                   "Plate-Weighted Crunch"
                 ],
                 "notes": "Round your lower back as you crunch. Maintain a mind-muscle connection with your 6-pack."
               }
             ]
           }
         ]
       },
       {
         "week": 2,
         "days": [
           {
             "name": "Upper #1",
             "exercises": [
               {
                 "name": "Cuffed Behind-The-Back Lateral Raise",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Cross-Body Cable Y-Raise",
                   "DB Lateral Raise"
                 ],
                 "notes": "Raise the cables up and out in a \"Y\" motion. Really try to connect with the middle delt fibers as you sweep the weight up and out."
               },
               {
                 "name": "Cross-Body Lat Pull-Around",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Half-Kneeling 1-Arm Lat Pulldown",
                   "Neutral-Grip Pullup"
                 ],
                 "notes": "Try to keep the cable and your wrist aligned in a straight line throughout the pull. Feel a nice, deep lat stretch at the top."
               },
               {
                 "name": "Low Incline Smith Machine Press",
                 "equipment": "MACHINE",
                 "sets": 4,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Low Incline Machine Press",
                   "Low Incline DB Press"
                 ],
                 "notes": "Set the bench at a ~15\u00b0 incline. 1 second pause on the chest on each rep while maintaining tension on the pecs."
               },
               {
                 "name": "Chest-Supported Machine Row",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Chest-Supported T-Bar Row",
                   "Helms Row"
                 ],
                 "notes": "Flare elbows out at roughly 45\u00b0 and squeeze your shoulder blades together hard at the top of each rep."
               },
               {
                 "name": "Overhead Cable Triceps Extension (Bar)",
                 "equipment": "CABLE",
                 "sets": 2,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Overhead Cable Triceps Extension (Rope)",
                   "DB Skull Crusher"
                 ],
                 "notes": "Feel a nasty stretch on the triceps throughout the entire negative. Pause for 1 second in the stretch part of each rep."
               },
               {
                 "name": "Straight-Bar Lat Prayer",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 12,
                 "rest": 90,
                 "substitutions": [
                   "Machine Lat Pullover",
                   "DB Lat Pullover"
                 ],
                 "notes": "Lean forward to get a big stretch on the lats at the top of the ROM and then stand upright as you squeeze your lats at the bottom."
               },
               {
                 "name": "Pec Deck (w/ Integrated Partials)",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 12,
                 "rest": 90,
                 "substitutions": [
                   "Bent-Over Cable Pec Flye (w/ Integrated Partials)",
                   "DB Flye (w/ Integrated Partials)"
                 ],
                 "notes": "Set up the pec deck to allow for maximum stretch. On all sets, alternate full-ROM reps and half-ROM reps (i.e. do 1 rep with full-ROM, then 1 rep half-ROM (in the stretched/bottom half), then 1 rep full-ROM, then 1 rep half-ROM). Repeat until you havee reached the target reps and an RPE of 9-10."
               }
             ]
           },
           {
             "name": "Lower #1",
             "exercises": [
               {
                 "name": "Seated Leg Curl",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Lying Leg Curl",
                   "Nordic Ham Curl"
                 ],
                 "notes": "Lean forward over the machine to get a maximum stretch in your hamstrings."
               },
               {
                 "name": "Machine Hip Adduction",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Cable Hip Adduction",
                   "Copenhagen Hip Adduction"
                 ],
                 "notes": "Mind-muscle connection with your inner thighs. These are great for adding thigh mass from the front! Push them hard!"
               },
               {
                 "name": "Hack Squat",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 6,
                 "rest": 180,
                 "substitutions": [
                   "Machine Squat",
                   "Front Squat"
                 ],
                 "notes": "We are using a reverse pyramid on this exercise. Warm-up as usual to your first working set for 4 reps. This first set will be your heaviest set. Then for set 2, drop the weight back ~10-15% and do 6 reps. Then for set 3, drop the weight back another 10-15% and do 8 reps."
               },
               {
                 "name": "Leg Extension",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "DB Step-Up",
                   "Reverse Nordic"
                 ],
                 "notes": "Set the seat back as far as it will go while still feeling comfortable. Grab the handles as hard as you can to pull your butt down into the seat. Use a 2-3 second negative. Feel your quads pulling apart on the negative."
               },
               {
                 "name": "Leg Press Calf Press",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 12,
                 "rest": 90,
                 "substitutions": [
                   "Donkey Calf Raise",
                   "Seated Calf Raise"
                 ],
                 "notes": "1-2 second pause at the bottom of each rep. Instead of just going up onto your toes, think about rolling your ankle back and forth on the balls of your feet."
               }
             ]
           },
           {
             "name": "Upper #2",
             "exercises": [
               {
                 "name": "Super-ROM Overhand Cable Row",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Overhand Machine Row",
                   "Arm-Out Single-Arm DB Row"
                 ],
                 "notes": "Set up a wide grip pulldown bar on a seated cable row. Using a double overhand grip, perform rows while leaning forward on the negative and then extend your torso to be upright as you finish the row."
               },
               {
                 "name": "Machine Shoulder Press",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Cable Shoulder Press",
                   "Seated DB Shoulder Press"
                 ],
                 "notes": "Ensure that your elbows break at least 90\u00b0. Mind-muscle connection with your delts. Smooth, controlled reps."
               },
               {
                 "name": "Assisted Pull-Up",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Lat Pulldown",
                   "Machine Pulldown"
                 ],
                 "notes": "Slow 2-3 second negative. Feel your lats pulling apart on the way down. Slight 0.5-1 second pause at the bottom, then lift your chest up and drive your elbows down as you lift yourself up. do not be afraid to use assistance. Keep the form tight and controlled!"
               },
               {
                 "name": "Paused Assisted Dip",
                 "equipment": "BODYWEIGHT",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Decline Machine Chest Press",
                   "Decline Barbell Press"
                 ],
                 "notes": "Slow 2-3 second negative. 1-2 second pause at the bottom. Explode with control on the way up. Go as deep as your shoulders comfortably allow, trying to at least break a 90\u00b0 elbow angle."
               },
               {
                 "name": "Inverse DB Zottman Curl",
                 "equipment": "DUMBBELL",
                 "sets": 2,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Slow-Eccentric DB Curl",
                   "Hammer Curl"
                 ],
                 "notes": "Do a hammer curl on the positive, then turn your palms facing up at the top and use a palms-up grip on the negative."
               },
               {
                 "name": "Super-ROM DB Lateral Raise",
                 "equipment": "DUMBBELL",
                 "sets": 3,
                 "reps": 12,
                 "rest": 0,
                 "substitutions": [
                   "Cable Upright Row",
                   "DB Lateral Raise"
                 ],
                 "notes": "Perform lateral raises as normal, except going until your hands are up overhead. As you break parallel, you will use more upper traps to move the weight. Feel free to squeeze your upper traps at the top. If you feel shoulder pain when going all the way up, try pointing your thumb up or simply stop at parallel and do normal lateral raises."
               },
               {
                 "name": "Cable Reverse Flye (Mechanical Dropset)",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 5,
                 "rest": 90,
                 "substitutions": [
                   "Reverse Pec Deck",
                   "Bent-Over Reverse DB Flye"
                 ],
                 "notes": "Youwill probably want to watch the video for this one. Take ~3 big steps back from the cable machine and do your first 5 reps. After those first 5 reps, immediately (without resting) take 1 step forward and do another 4 reps. Then (without resting) take another step forward and do at least another 3 reps (or until you hit RPE 9-10)."
               }
             ]
           },
           {
             "name": "Lower #2",
             "exercises": [
               {
                 "name": "Lying Leg Curl",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Seated Leg Curl",
                   "Nordic Ham Curl"
                 ],
                 "notes": "Set the machine so that you get the biggest stretch possible at the bottom. Prevent your butt from popping up as you curl. Once you get to the full squeeze, continue with partial reps on the last set."
               },
               {
                 "name": "Leg Press",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 8,
                 "rest": 90,
                 "substitutions": [
                   "Belt Squat",
                   "High-Bar Back Squat"
                 ],
                 "notes": "Feet lower on the platform for more quad focus. Get as deep as you can without excessive back rounding. Control the negative and do a slight pause at the bottom of each rep. Try to add a little weight each week at the same rep count."
               },
               {
                 "name": "Paused Barbell RDL",
                 "equipment": "BARBELL",
                 "sets": 2,
                 "reps": 8,
                 "rest": 180,
                 "substitutions": [
                   "Paused DB RDL",
                   "Glute-Ham Raise"
                 ],
                 "notes": "The RPE is intentionally low here because these will cause a lot of muscle damage. do not be tempted to go too heavy. 1 second pause at the bottom of each rep. To keep tension on the hamstrings, stop about 75% of the way to full lockout on each rep (i.e. stay in the bottom 3/4 of the range of motion)."
               },
               {
                 "name": "A1: Machine Hip Adduction",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 0,
                 "substitutions": [
                   "Cable Hip Adduction",
                   "Copenhagen Hip Adduction"
                 ],
                 "notes": "Mind-muscle connection with your inner thighs. These are great for adding thigh mass from the front! Push them hard!"
               },
               {
                 "name": "A2: Sissy Squat",
                 "equipment": "BODYWEIGHT",
                 "sets": 3,
                 "reps": 10,
                 "rest": 0,
                 "substitutions": [
                   "Leg Extension",
                   "Goblet Squat"
                 ],
                 "notes": "Allow yourself to come up onto your toes and push your knees forward past your toes. This is safe for the knees. If you feel knee pain doing them, though, feel free to go with a substitution. They may feel awkward at first, but they really underrated for the quads! do not give up on them too quickly."
               },
               {
                 "name": "Standing Calf Raise",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Leg Press Calf Press",
                   "Donkey Calf Raise"
                 ],
                 "notes": "1-2 second pause at the bottom of each rep. Instead of just going up onto your toes, think about rolling your ankle back and forth on the balls of your feet."
               }
             ]
           },
           {
             "name": "Arms & Weak Points",
             "exercises": [
               {
                 "name": "Incline Cable Lateral Raise",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 60,
                 "notes": "Decide on your weak point using The Weak Point Table in your Hypertrophy Handbook. Perform ONE of the exercises listed under Exercise 1 for the sets and reps provided here.",
                 "substitutions": [
                   "DB Lateral Raise",
                   "Machine Lateral Raise"
                 ]
               },
               {
                 "name": "Bayesian Cable Curl",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "DB Incline Curl",
                   "DB Scott Curl"
                 ],
                 "notes": "If you have a left-right bicep size imbalance, do these 1 arm at a time, starting with the weaker arm. Take the weaker arm to an RPE of 9-10. Then match the reps with the other arm (stop once you havee matched the reps, even if the RPE is lower). If you do not have a size imbalance, do these both arms at the same time."
               },
               {
                 "name": "Seated DB French Press",
                 "equipment": "DUMBBELL",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "EZ-bar Skull Crusher",
                   "DB Skull Crusher"
                 ],
                 "notes": "Place both palms under the head of a dumbbell and perform overhead extensions. Feel a deep stretch on your triceps at the bottom. Avoid pausing at the top of each rep."
               },
               {
                 "name": "Bottom-2/3 Constant Tension Preacher Curl",
                 "equipment": "OTHER",
                 "sets": 2,
                 "reps": 12,
                 "rest": 90,
                 "substitutions": [
                   "Bottom-2/3 EZ-Bar Curl",
                   "Spider Curl"
                 ],
                 "notes": "Stay in the bottom 2/3 of the curl. do not squeeze all the way up to the top. Keep your triceps firmly pinned against the pad as you curl. No pausing at the top or bottom: constant tension on the biceps!"
               },
               {
                 "name": "Cable Triceps Kickback",
                 "equipment": "CABLE",
                 "sets": 2,
                 "reps": 12,
                 "rest": 90,
                 "substitutions": [
                   "Bench Dip",
                   "DB Triceps Kickback"
                 ],
                 "notes": "There are two ways you can do this: upright or bent over. Choose the one that feels more comfortable for you. The main thing is that when you are in the full squeeze, your shoulder should be positioned back behind your torso."
               },
               {
                 "name": "Cable Crunch",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Machine Crunch",
                   "Plate-Weighted Crunch"
                 ],
                 "notes": "Round your lower back as you crunch. Maintain a mind-muscle connection with your 6-pack."
               }
             ]
           }
         ]
       },
       {
         "week": 3,
         "days": [
           {
             "name": "Upper #1",
             "exercises": [
               {
                 "name": "Cuffed Behind-The-Back Lateral Raise",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Cross-Body Cable Y-Raise",
                   "DB Lateral Raise"
                 ],
                 "notes": "Raise the cables up and out in a \"Y\" motion. Really try to connect with the middle delt fibers as you sweep the weight up and out."
               },
               {
                 "name": "Cross-Body Lat Pull-Around",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Half-Kneeling 1-Arm Lat Pulldown",
                   "Neutral-Grip Pullup"
                 ],
                 "notes": "Try to keep the cable and your wrist aligned in a straight line throughout the pull. Feel a nice, deep lat stretch at the top."
               },
               {
                 "name": "Low Incline Smith Machine Press",
                 "equipment": "MACHINE",
                 "sets": 4,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Low Incline Machine Press",
                   "Low Incline DB Press"
                 ],
                 "notes": "Set the bench at a ~15\u00b0 incline. 1 second pause on the chest on each rep while maintaining tension on the pecs."
               },
               {
                 "name": "Chest-Supported Machine Row",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Chest-Supported T-Bar Row",
                   "Helms Row"
                 ],
                 "notes": "Flare elbows out at roughly 45\u00b0 and squeeze your shoulder blades together hard at the top of each rep."
               },
               {
                 "name": "Overhead Cable Triceps Extension (Bar)",
                 "equipment": "CABLE",
                 "sets": 2,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Overhead Cable Triceps Extension (Rope)",
                   "DB Skull Crusher"
                 ],
                 "notes": "Feel a nasty stretch on the triceps throughout the entire negative. Pause for 1 second in the stretch part of each rep."
               },
               {
                 "name": "Straight-Bar Lat Prayer",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 12,
                 "rest": 90,
                 "substitutions": [
                   "Machine Lat Pullover",
                   "DB Lat Pullover"
                 ],
                 "notes": "Lean forward to get a big stretch on the lats at the top of the ROM and then stand upright as you squeeze your lats at the bottom."
               },
               {
                 "name": "Pec Deck (w/ Integrated Partials)",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 12,
                 "rest": 90,
                 "substitutions": [
                   "Bent-Over Cable Pec Flye (w/ Integrated Partials)",
                   "DB Flye (w/ Integrated Partials)"
                 ],
                 "notes": "Set up the pec deck to allow for maximum stretch. On all sets, alternate full-ROM reps and half-ROM reps (i.e. do 1 rep with full-ROM, then 1 rep half-ROM (in the stretched/bottom half), then 1 rep full-ROM, then 1 rep half-ROM). Repeat until you havee reached the target reps and an RPE of 9-10."
               }
             ]
           },
           {
             "name": "Lower #1",
             "exercises": [
               {
                 "name": "Seated Leg Curl",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Lying Leg Curl",
                   "Nordic Ham Curl"
                 ],
                 "notes": "Lean forward over the machine to get a maximum stretch in your hamstrings."
               },
               {
                 "name": "Machine Hip Adduction",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Cable Hip Adduction",
                   "Copenhagen Hip Adduction"
                 ],
                 "notes": "Mind-muscle connection with your inner thighs. These are great for adding thigh mass from the front! Push them hard!"
               },
               {
                 "name": "Hack Squat",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 6,
                 "rest": 180,
                 "substitutions": [
                   "Machine Squat",
                   "Front Squat"
                 ],
                 "notes": "We are using a reverse pyramid on this exercise. Warm-up as usual to your first working set for 4 reps. This first set will be your heaviest set. Then for set 2, drop the weight back ~10-15% and do 6 reps. Then for set 3, drop the weight back another 10-15% and do 8 reps."
               },
               {
                 "name": "Leg Extension",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "DB Step-Up",
                   "Reverse Nordic"
                 ],
                 "notes": "Set the seat back as far as it will go while still feeling comfortable. Grab the handles as hard as you can to pull your butt down into the seat. Use a 2-3 second negative. Feel your quads pulling apart on the negative."
               },
               {
                 "name": "Leg Press Calf Press",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 12,
                 "rest": 90,
                 "substitutions": [
                   "Donkey Calf Raise",
                   "Seated Calf Raise"
                 ],
                 "notes": "1-2 second pause at the bottom of each rep. Instead of just going up onto your toes, think about rolling your ankle back and forth on the balls of your feet."
               }
             ]
           },
           {
             "name": "Upper #2",
             "exercises": [
               {
                 "name": "Super-ROM Overhand Cable Row",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Overhand Machine Row",
                   "Arm-Out Single-Arm DB Row"
                 ],
                 "notes": "Set up a wide grip pulldown bar on a seated cable row. Using a double overhand grip, perform rows while leaning forward on the negative and then extend your torso to be upright as you finish the row."
               },
               {
                 "name": "Machine Shoulder Press",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Cable Shoulder Press",
                   "Seated DB Shoulder Press"
                 ],
                 "notes": "Ensure that your elbows break at least 90\u00b0. Mind-muscle connection with your delts. Smooth, controlled reps."
               },
               {
                 "name": "Assisted Pull-Up",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Lat Pulldown",
                   "Machine Pulldown"
                 ],
                 "notes": "Slow 2-3 second negative. Feel your lats pulling apart on the way down. Slight 0.5-1 second pause at the bottom, then lift your chest up and drive your elbows down as you lift yourself up. do not be afraid to use assistance. Keep the form tight and controlled!"
               },
               {
                 "name": "Paused Assisted Dip",
                 "equipment": "BODYWEIGHT",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Decline Machine Chest Press",
                   "Decline Barbell Press"
                 ],
                 "notes": "Slow 2-3 second negative. 1-2 second pause at the bottom. Explode with control on the way up. Go as deep as your shoulders comfortably allow, trying to at least break a 90\u00b0 elbow angle."
               },
               {
                 "name": "Inverse DB Zottman Curl",
                 "equipment": "DUMBBELL",
                 "sets": 2,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Slow-Eccentric DB Curl",
                   "Hammer Curl"
                 ],
                 "notes": "Do a hammer curl on the positive, then turn your palms facing up at the top and use a palms-up grip on the negative."
               },
               {
                 "name": "Super-ROM DB Lateral Raise",
                 "equipment": "DUMBBELL",
                 "sets": 3,
                 "reps": 12,
                 "rest": 0,
                 "substitutions": [
                   "Cable Upright Row",
                   "DB Lateral Raise"
                 ],
                 "notes": "Perform lateral raises as normal, except going until your hands are up overhead. As you break parallel, you will use more upper traps to move the weight. Feel free to squeeze your upper traps at the top. If you feel shoulder pain when going all the way up, try pointing your thumb up or simply stop at parallel and do normal lateral raises."
               },
               {
                 "name": "Cable Reverse Flye (Mechanical Dropset)",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 5,
                 "rest": 90,
                 "substitutions": [
                   "Reverse Pec Deck",
                   "Bent-Over Reverse DB Flye"
                 ],
                 "notes": "Youwill probably want to watch the video for this one. Take ~3 big steps back from the cable machine and do your first 5 reps. After those first 5 reps, immediately (without resting) take 1 step forward and do another 4 reps. Then (without resting) take another step forward and do at least another 3 reps (or until you hit RPE 9-10)."
               }
             ]
           },
           {
             "name": "Lower #2",
             "exercises": [
               {
                 "name": "Lying Leg Curl",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Seated Leg Curl",
                   "Nordic Ham Curl"
                 ],
                 "notes": "Set the machine so that you get the biggest stretch possible at the bottom. Prevent your butt from popping up as you curl. Once you get to the full squeeze, continue with partial reps on the last set."
               },
               {
                 "name": "Leg Press",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 8,
                 "rest": 90,
                 "substitutions": [
                   "Belt Squat",
                   "High-Bar Back Squat"
                 ],
                 "notes": "Feet lower on the platform for more quad focus. Get as deep as you can without excessive back rounding. Control the negative and do a slight pause at the bottom of each rep. Try to add a little weight each week at the same rep count."
               },
               {
                 "name": "Paused Barbell RDL",
                 "equipment": "BARBELL",
                 "sets": 2,
                 "reps": 8,
                 "rest": 180,
                 "substitutions": [
                   "Paused DB RDL",
                   "Glute-Ham Raise"
                 ],
                 "notes": "The RPE is intentionally low here because these will cause a lot of muscle damage. do not be tempted to go too heavy. 1 second pause at the bottom of each rep. To keep tension on the hamstrings, stop about 75% of the way to full lockout on each rep (i.e. stay in the bottom 3/4 of the range of motion)."
               },
               {
                 "name": "A1: Machine Hip Adduction",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 0,
                 "substitutions": [
                   "Cable Hip Adduction",
                   "Copenhagen Hip Adduction"
                 ],
                 "notes": "Mind-muscle connection with your inner thighs. These are great for adding thigh mass from the front! Push them hard!"
               },
               {
                 "name": "A2: Sissy Squat",
                 "equipment": "BODYWEIGHT",
                 "sets": 3,
                 "reps": 10,
                 "rest": 0,
                 "substitutions": [
                   "Leg Extension",
                   "Goblet Squat"
                 ],
                 "notes": "Allow yourself to come up onto your toes and push your knees forward past your toes. This is safe for the knees. If you feel knee pain doing them, though, feel free to go with a substitution. They may feel awkward at first, but they really underrated for the quads! do not give up on them too quickly."
               },
               {
                 "name": "Standing Calf Raise",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Leg Press Calf Press",
                   "Donkey Calf Raise"
                 ],
                 "notes": "1-2 second pause at the bottom of each rep. Instead of just going up onto your toes, think about rolling your ankle back and forth on the balls of your feet."
               }
             ]
           },
           {
             "name": "Arms & Weak Points",
             "exercises": [
               {
                 "name": "Incline Cable Lateral Raise",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 60,
                 "notes": "Decide on your weak point using The Weak Point Table in your Hypertrophy Handbook. Perform ONE of the exercises listed under Exercise 1 for the sets and reps provided here.",
                 "substitutions": [
                   "DB Lateral Raise",
                   "Machine Lateral Raise"
                 ]
               },
               {
                 "name": "Bayesian Cable Curl",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "DB Incline Curl",
                   "DB Scott Curl"
                 ],
                 "notes": "If you have a left-right bicep size imbalance, do these 1 arm at a time, starting with the weaker arm. Take the weaker arm to an RPE of 9-10. Then match the reps with the other arm (stop once you havee matched the reps, even if the RPE is lower). If you do not have a size imbalance, do these both arms at the same time."
               },
               {
                 "name": "Seated DB French Press",
                 "equipment": "DUMBBELL",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "EZ-bar Skull Crusher",
                   "DB Skull Crusher"
                 ],
                 "notes": "Place both palms under the head of a dumbbell and perform overhead extensions. Feel a deep stretch on your triceps at the bottom. Avoid pausing at the top of each rep."
               },
               {
                 "name": "Bottom-2/3 Constant Tension Preacher Curl",
                 "equipment": "OTHER",
                 "sets": 2,
                 "reps": 12,
                 "rest": 90,
                 "substitutions": [
                   "Bottom-2/3 EZ-Bar Curl",
                   "Spider Curl"
                 ],
                 "notes": "Stay in the bottom 2/3 of the curl. do not squeeze all the way up to the top. Keep your triceps firmly pinned against the pad as you curl. No pausing at the top or bottom: constant tension on the biceps!"
               },
               {
                 "name": "Cable Triceps Kickback",
                 "equipment": "CABLE",
                 "sets": 2,
                 "reps": 12,
                 "rest": 90,
                 "substitutions": [
                   "Bench Dip",
                   "DB Triceps Kickback"
                 ],
                 "notes": "There are two ways you can do this: upright or bent over. Choose the one that feels more comfortable for you. The main thing is that when you are in the full squeeze, your shoulder should be positioned back behind your torso."
               },
               {
                 "name": "Cable Crunch",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Machine Crunch",
                   "Plate-Weighted Crunch"
                 ],
                 "notes": "Round your lower back as you crunch. Maintain a mind-muscle connection with your 6-pack."
               }
             ]
           }
         ]
       },
       {
         "week": 4,
         "days": [
           {
             "name": "Upper #1",
             "exercises": [
               {
                 "name": "Cuffed Behind-The-Back Lateral Raise",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Cross-Body Cable Y-Raise",
                   "DB Lateral Raise"
                 ],
                 "notes": "Raise the cables up and out in a \"Y\" motion. Really try to connect with the middle delt fibers as you sweep the weight up and out."
               },
               {
                 "name": "Cross-Body Lat Pull-Around",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Half-Kneeling 1-Arm Lat Pulldown",
                   "Neutral-Grip Pullup"
                 ],
                 "notes": "Try to keep the cable and your wrist aligned in a straight line throughout the pull. Feel a nice, deep lat stretch at the top."
               },
               {
                 "name": "Low Incline Smith Machine Press",
                 "equipment": "MACHINE",
                 "sets": 4,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Low Incline Machine Press",
                   "Low Incline DB Press"
                 ],
                 "notes": "Set the bench at a ~15\u00b0 incline. 1 second pause on the chest on each rep while maintaining tension on the pecs."
               },
               {
                 "name": "Chest-Supported Machine Row",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Chest-Supported T-Bar Row",
                   "Helms Row"
                 ],
                 "notes": "Flare elbows out at roughly 45\u00b0 and squeeze your shoulder blades together hard at the top of each rep."
               },
               {
                 "name": "Overhead Cable Triceps Extension (Bar)",
                 "equipment": "CABLE",
                 "sets": 2,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Overhead Cable Triceps Extension (Rope)",
                   "DB Skull Crusher"
                 ],
                 "notes": "Feel a nasty stretch on the triceps throughout the entire negative. Pause for 1 second in the stretch part of each rep."
               },
               {
                 "name": "Straight-Bar Lat Prayer",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 12,
                 "rest": 90,
                 "substitutions": [
                   "Machine Lat Pullover",
                   "DB Lat Pullover"
                 ],
                 "notes": "Lean forward to get a big stretch on the lats at the top of the ROM and then stand upright as you squeeze your lats at the bottom."
               },
               {
                 "name": "Pec Deck (w/ Integrated Partials)",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 12,
                 "rest": 90,
                 "substitutions": [
                   "Bent-Over Cable Pec Flye (w/ Integrated Partials)",
                   "DB Flye (w/ Integrated Partials)"
                 ],
                 "notes": "Set up the pec deck to allow for maximum stretch. On all sets, alternate full-ROM reps and half-ROM reps (i.e. do 1 rep with full-ROM, then 1 rep half-ROM (in the stretched/bottom half), then 1 rep full-ROM, then 1 rep half-ROM). Repeat until you havee reached the target reps and an RPE of 9-10."
               }
             ]
           },
           {
             "name": "Lower #1",
             "exercises": [
               {
                 "name": "Seated Leg Curl",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Lying Leg Curl",
                   "Nordic Ham Curl"
                 ],
                 "notes": "Lean forward over the machine to get a maximum stretch in your hamstrings."
               },
               {
                 "name": "Machine Hip Adduction",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Cable Hip Adduction",
                   "Copenhagen Hip Adduction"
                 ],
                 "notes": "Mind-muscle connection with your inner thighs. These are great for adding thigh mass from the front! Push them hard!"
               },
               {
                 "name": "Hack Squat",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 6,
                 "rest": 180,
                 "substitutions": [
                   "Machine Squat",
                   "Front Squat"
                 ],
                 "notes": "We are using a reverse pyramid on this exercise. Warm-up as usual to your first working set for 4 reps. This first set will be your heaviest set. Then for set 2, drop the weight back ~10-15% and do 6 reps. Then for set 3, drop the weight back another 10-15% and do 8 reps."
               },
               {
                 "name": "Leg Extension",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "DB Step-Up",
                   "Reverse Nordic"
                 ],
                 "notes": "Set the seat back as far as it will go while still feeling comfortable. Grab the handles as hard as you can to pull your butt down into the seat. Use a 2-3 second negative. Feel your quads pulling apart on the negative."
               },
               {
                 "name": "Leg Press Calf Press",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 12,
                 "rest": 90,
                 "substitutions": [
                   "Donkey Calf Raise",
                   "Seated Calf Raise"
                 ],
                 "notes": "1-2 second pause at the bottom of each rep. Instead of just going up onto your toes, think about rolling your ankle back and forth on the balls of your feet."
               }
             ]
           },
           {
             "name": "Upper #2",
             "exercises": [
               {
                 "name": "Super-ROM Overhand Cable Row",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Overhand Machine Row",
                   "Arm-Out Single-Arm DB Row"
                 ],
                 "notes": "Set up a wide grip pulldown bar on a seated cable row. Using a double overhand grip, perform rows while leaning forward on the negative and then extend your torso to be upright as you finish the row."
               },
               {
                 "name": "Machine Shoulder Press",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Cable Shoulder Press",
                   "Seated DB Shoulder Press"
                 ],
                 "notes": "Ensure that your elbows break at least 90\u00b0. Mind-muscle connection with your delts. Smooth, controlled reps."
               },
               {
                 "name": "Assisted Pull-Up",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Lat Pulldown",
                   "Machine Pulldown"
                 ],
                 "notes": "Slow 2-3 second negative. Feel your lats pulling apart on the way down. Slight 0.5-1 second pause at the bottom, then lift your chest up and drive your elbows down as you lift yourself up. do not be afraid to use assistance. Keep the form tight and controlled!"
               },
               {
                 "name": "Paused Assisted Dip",
                 "equipment": "BODYWEIGHT",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Decline Machine Chest Press",
                   "Decline Barbell Press"
                 ],
                 "notes": "Slow 2-3 second negative. 1-2 second pause at the bottom. Explode with control on the way up. Go as deep as your shoulders comfortably allow, trying to at least break a 90\u00b0 elbow angle."
               },
               {
                 "name": "Inverse DB Zottman Curl",
                 "equipment": "DUMBBELL",
                 "sets": 2,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Slow-Eccentric DB Curl",
                   "Hammer Curl"
                 ],
                 "notes": "Do a hammer curl on the positive, then turn your palms facing up at the top and use a palms-up grip on the negative."
               },
               {
                 "name": "Super-ROM DB Lateral Raise",
                 "equipment": "DUMBBELL",
                 "sets": 3,
                 "reps": 12,
                 "rest": 0,
                 "substitutions": [
                   "Cable Upright Row",
                   "DB Lateral Raise"
                 ],
                 "notes": "Perform lateral raises as normal, except going until your hands are up overhead. As you break parallel, you will use more upper traps to move the weight. Feel free to squeeze your upper traps at the top. If you feel shoulder pain when going all the way up, try pointing your thumb up or simply stop at parallel and do normal lateral raises."
               },
               {
                 "name": "Cable Reverse Flye (Mechanical Dropset)",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 5,
                 "rest": 90,
                 "substitutions": [
                   "Reverse Pec Deck",
                   "Bent-Over Reverse DB Flye"
                 ],
                 "notes": "Youwill probably want to watch the video for this one. Take ~3 big steps back from the cable machine and do your first 5 reps. After those first 5 reps, immediately (without resting) take 1 step forward and do another 4 reps. Then (without resting) take another step forward and do at least another 3 reps (or until you hit RPE 9-10)."
               }
             ]
           },
           {
             "name": "Lower #2",
             "exercises": [
               {
                 "name": "Lying Leg Curl",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Seated Leg Curl",
                   "Nordic Ham Curl"
                 ],
                 "notes": "Set the machine so that you get the biggest stretch possible at the bottom. Prevent your butt from popping up as you curl. Once you get to the full squeeze, continue with partial reps on the last set."
               },
               {
                 "name": "Leg Press",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 8,
                 "rest": 90,
                 "substitutions": [
                   "Belt Squat",
                   "High-Bar Back Squat"
                 ],
                 "notes": "Feet lower on the platform for more quad focus. Get as deep as you can without excessive back rounding. Control the negative and do a slight pause at the bottom of each rep. Try to add a little weight each week at the same rep count."
               },
               {
                 "name": "Paused Barbell RDL",
                 "equipment": "BARBELL",
                 "sets": 2,
                 "reps": 8,
                 "rest": 180,
                 "substitutions": [
                   "Paused DB RDL",
                   "Glute-Ham Raise"
                 ],
                 "notes": "The RPE is intentionally low here because these will cause a lot of muscle damage. do not be tempted to go too heavy. 1 second pause at the bottom of each rep. To keep tension on the hamstrings, stop about 75% of the way to full lockout on each rep (i.e. stay in the bottom 3/4 of the range of motion)."
               },
               {
                 "name": "A1: Machine Hip Adduction",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 0,
                 "substitutions": [
                   "Cable Hip Adduction",
                   "Copenhagen Hip Adduction"
                 ],
                 "notes": "Mind-muscle connection with your inner thighs. These are great for adding thigh mass from the front! Push them hard!"
               },
               {
                 "name": "A2: Sissy Squat",
                 "equipment": "BODYWEIGHT",
                 "sets": 3,
                 "reps": 10,
                 "rest": 0,
                 "substitutions": [
                   "Leg Extension",
                   "Goblet Squat"
                 ],
                 "notes": "Allow yourself to come up onto your toes and push your knees forward past your toes. This is safe for the knees. If you feel knee pain doing them, though, feel free to go with a substitution. They may feel awkward at first, but they really underrated for the quads! do not give up on them too quickly."
               },
               {
                 "name": "Standing Calf Raise",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Leg Press Calf Press",
                   "Donkey Calf Raise"
                 ],
                 "notes": "1-2 second pause at the bottom of each rep. Instead of just going up onto your toes, think about rolling your ankle back and forth on the balls of your feet."
               }
             ]
           },
           {
             "name": "Arms & Weak Points",
             "exercises": [
               {
                 "name": "Incline Cable Lateral Raise",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 60,
                 "notes": "Decide on your weak point using The Weak Point Table in your Hypertrophy Handbook. Perform ONE of the exercises listed under Exercise 1 for the sets and reps provided here.",
                 "substitutions": [
                   "DB Lateral Raise",
                   "Machine Lateral Raise"
                 ]
               },
               {
                 "name": "Bayesian Cable Curl",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "DB Incline Curl",
                   "DB Scott Curl"
                 ],
                 "notes": "If you have a left-right bicep size imbalance, do these 1 arm at a time, starting with the weaker arm. Take the weaker arm to an RPE of 9-10. Then match the reps with the other arm (stop once you havee matched the reps, even if the RPE is lower). If you do not have a size imbalance, do these both arms at the same time."
               },
               {
                 "name": "Seated DB French Press",
                 "equipment": "DUMBBELL",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "EZ-bar Skull Crusher",
                   "DB Skull Crusher"
                 ],
                 "notes": "Place both palms under the head of a dumbbell and perform overhead extensions. Feel a deep stretch on your triceps at the bottom. Avoid pausing at the top of each rep."
               },
               {
                 "name": "Bottom-2/3 Constant Tension Preacher Curl",
                 "equipment": "OTHER",
                 "sets": 2,
                 "reps": 12,
                 "rest": 90,
                 "substitutions": [
                   "Bottom-2/3 EZ-Bar Curl",
                   "Spider Curl"
                 ],
                 "notes": "Stay in the bottom 2/3 of the curl. do not squeeze all the way up to the top. Keep your triceps firmly pinned against the pad as you curl. No pausing at the top or bottom: constant tension on the biceps!"
               },
               {
                 "name": "Cable Triceps Kickback",
                 "equipment": "CABLE",
                 "sets": 2,
                 "reps": 12,
                 "rest": 90,
                 "substitutions": [
                   "Bench Dip",
                   "DB Triceps Kickback"
                 ],
                 "notes": "There are two ways you can do this: upright or bent over. Choose the one that feels more comfortable for you. The main thing is that when you are in the full squeeze, your shoulder should be positioned back behind your torso."
               },
               {
                 "name": "Cable Crunch",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Machine Crunch",
                   "Plate-Weighted Crunch"
                 ],
                 "notes": "Round your lower back as you crunch. Maintain a mind-muscle connection with your 6-pack."
               }
             ]
           }
         ]
       },
       {
         "week": 5,
         "days": [
           {
             "name": "Upper #1",
             "exercises": [
               {
                 "name": "Cuffed Behind-The-Back Lateral Raise",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Cross-Body Cable Y-Raise",
                   "DB Lateral Raise"
                 ],
                 "notes": "Raise the cables up and out in a \"Y\" motion. Really try to connect with the middle delt fibers as you sweep the weight up and out."
               },
               {
                 "name": "Cross-Body Lat Pull-Around",
                 "equipment": "OTHER",
                 "sets": 2,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Half-Kneeling 1-Arm Lat Pulldown",
                   "Neutral-Grip Pullup"
                 ],
                 "notes": "Try to keep the cable and your wrist aligned in a straight line throughout the pull. Feel a nice, deep lat stretch at the top."
               },
               {
                 "name": "Low Incline Smith Machine Press",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Low Incline Machine Press",
                   "Low Incline DB Press"
                 ],
                 "notes": "Set the bench at a ~15\u00b0 incline. 1 second pause on the chest on each rep while maintaining tension on the pecs."
               },
               {
                 "name": "Chest-Supported Machine Row",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Chest-Supported T-Bar Row",
                   "Helms Row"
                 ],
                 "notes": "Flare elbows out at roughly 45\u00b0 and squeeze your shoulder blades together hard at the top of each rep."
               },
               {
                 "name": "Overhead Cable Triceps Extension (Bar)",
                 "equipment": "CABLE",
                 "sets": 2,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Overhead Cable Triceps Extension (Rope)",
                   "DB Skull Crusher"
                 ],
                 "notes": "Feel a nasty stretch on the triceps throughout the entire negative. Pause for 1 second in the stretch part of each rep."
               },
               {
                 "name": "Straight-Bar Lat Prayer",
                 "equipment": "CABLE",
                 "sets": 2,
                 "reps": 12,
                 "rest": 90,
                 "substitutions": [
                   "Machine Lat Pullover",
                   "DB Lat Pullover"
                 ],
                 "notes": "Lean forward to get a big stretch on the lats at the top of the ROM and then stand upright as you squeeze your lats at the bottom."
               },
               {
                 "name": "Pec Deck (w/ Integrated Partials)",
                 "equipment": "MACHINE",
                 "sets": 2,
                 "reps": 12,
                 "rest": 90,
                 "substitutions": [
                   "Bent-Over Cable Pec Flye (w/ Integrated Partials)",
                   "DB Flye (w/ Integrated Partials)"
                 ],
                 "notes": "Set up the pec deck to allow for maximum stretch. On all sets, alternate full-ROM reps and half-ROM reps (i.e. do 1 rep with full-ROM, then 1 rep half-ROM (in the stretched/bottom half), then 1 rep full-ROM, then 1 rep half-ROM). Repeat until you havee reached the target reps and an RPE of 9-10."
               }
             ]
           },
           {
             "name": "Lower #1",
             "exercises": [
               {
                 "name": "Seated Leg Curl",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Lying Leg Curl",
                   "Nordic Ham Curl"
                 ],
                 "notes": "Lean forward over the machine to get a maximum stretch in your hamstrings."
               },
               {
                 "name": "Machine Hip Adduction",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Cable Hip Adduction",
                   "Copenhagen Hip Adduction"
                 ],
                 "notes": "Mind-muscle connection with your inner thighs. These are great for adding thigh mass from the front! Push them hard!"
               },
               {
                 "name": "Hack Squat",
                 "equipment": "MACHINE",
                 "sets": 2,
                 "reps": 5,
                 "rest": 180,
                 "substitutions": [
                   "Machine Squat",
                   "Front Squat"
                 ],
                 "notes": "We are using a reverse pyramid on this exercise. Warm-up as usual to your first working set for 4 reps. This first set will be your heaviest set. Then for set 2, drop the weight back ~10-15% and do 6 reps. No third set this week."
               },
               {
                 "name": "Leg Extension",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "DB Step-Up",
                   "Reverse Nordic"
                 ],
                 "notes": "Set the seat back as far as it will go while still feeling comfortable. Grab the handles as hard as you can to pull your butt down into the seat. Use a 2-3 second negative. Feel your quads pulling apart on the negative."
               },
               {
                 "name": "Leg Press Calf Press",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 12,
                 "rest": 90,
                 "substitutions": [
                   "Donkey Calf Raise",
                   "Seated Calf Raise"
                 ],
                 "notes": "1-2 second pause at the bottom of each rep. Instead of just going up onto your toes, think about rolling your ankle back and forth on the balls of your feet."
               }
             ]
           },
           {
             "name": "Upper #2",
             "exercises": [
               {
                 "name": "Super-ROM Overhand Cable Row",
                 "equipment": "CABLE",
                 "sets": 2,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Overhand Machine Row",
                   "Arm-Out Single-Arm DB Row"
                 ],
                 "notes": "Set up a wide grip pulldown bar on a seated cable row. Using a double overhand grip, perform rows while leaning forward on the negative and then extend your torso to be upright as you finish the row."
               },
               {
                 "name": "Machine Shoulder Press",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Cable Shoulder Press",
                   "Seated DB Shoulder Press"
                 ],
                 "notes": "Ensure that your elbows break at least 90\u00b0. Mind-muscle connection with your delts. Smooth, controlled reps."
               },
               {
                 "name": "Assisted Pull-Up",
                 "equipment": "OTHER",
                 "sets": 2,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Lat Pulldown",
                   "Machine Pulldown"
                 ],
                 "notes": "Slow 2-3 second negative. Feel your lats pulling apart on the way down. Slight 0.5-1 second pause at the bottom, then lift your chest up and drive your elbows down as you lift yourself up. do not be afraid to use assistance. Keep the form tight and controlled!"
               },
               {
                 "name": "Paused Assisted Dip",
                 "equipment": "BODYWEIGHT",
                 "sets": 2,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Decline Machine Chest Press",
                   "Decline Barbell Press"
                 ],
                 "notes": "Slow 2-3 second negative. 1-2 second pause at the bottom. Explode with control on the way up. Go as deep as your shoulders comfortably allow, trying to at least break a 90\u00b0 elbow angle."
               },
               {
                 "name": "Inverse DB Zottman Curl",
                 "equipment": "DUMBBELL",
                 "sets": 2,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Slow-Eccentric DB Curl",
                   "Hammer Curl"
                 ],
                 "notes": "Do a hammer curl on the positive, then turn your palms facing up at the top and use a palms-up grip on the negative."
               },
               {
                 "name": "Super-ROM DB Lateral Raise",
                 "equipment": "DUMBBELL",
                 "sets": 3,
                 "reps": 12,
                 "rest": 0,
                 "substitutions": [
                   "Cable Upright Row",
                   "DB Lateral Raise"
                 ],
                 "notes": "Perform lateral raises as normal, except going until your hands are up overhead. As you break parallel, you will use more upper traps to move the weight. Feel free to squeeze your upper traps at the top. If you feel shoulder pain when going all the way up, try pointing your thumb up or simply stop at parallel and do normal lateral raises."
               },
               {
                 "name": "Cable Reverse Flye (Mechanical Dropset)",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 5,
                 "rest": 90,
                 "substitutions": [
                   "Reverse Pec Deck",
                   "Bent-Over Reverse DB Flye"
                 ],
                 "notes": "Youwill probably want to watch the video for this one. Take ~3 big steps back from the cable machine and do your first 5 reps. After those first 5 reps, immediately (without resting) take 1 step forward and do another 4 reps. Then (without resting) take another step forward and do at least another 3 reps (or until you hit RPE 9-10)."
               }
             ]
           },
           {
             "name": "Lower #2",
             "exercises": [
               {
                 "name": "Lying Leg Curl",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Seated Leg Curl",
                   "Nordic Ham Curl"
                 ],
                 "notes": "Set the machine so that you get the biggest stretch possible at the bottom. Prevent your butt from popping up as you curl. Once you get to the full squeeze, continue with partial reps on the last set."
               },
               {
                 "name": "Leg Press",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 8,
                 "rest": 90,
                 "substitutions": [
                   "Belt Squat",
                   "High-Bar Back Squat"
                 ],
                 "notes": "Feet lower on the platform for more quad focus. Get as deep as you can without excessive back rounding. Control the negative and do a slight pause at the bottom of each rep. Try to add a little weight each week at the same rep count."
               },
               {
                 "name": "Paused Barbell RDL",
                 "equipment": "BARBELL",
                 "sets": 2,
                 "reps": 8,
                 "rest": 180,
                 "substitutions": [
                   "Paused DB RDL",
                   "Glute-Ham Raise"
                 ],
                 "notes": "The RPE is intentionally low here because these will cause a lot of muscle damage. do not be tempted to go too heavy. 1 second pause at the bottom of each rep. To keep tension on the hamstrings, stop about 75% of the way to full lockout on each rep (i.e. stay in the bottom 3/4 of the range of motion)."
               },
               {
                 "name": "A1: Machine Hip Adduction",
                 "equipment": "MACHINE",
                 "sets": 2,
                 "reps": 10,
                 "rest": 0,
                 "substitutions": [
                   "Cable Hip Adduction",
                   "Copenhagen Hip Adduction"
                 ],
                 "notes": "Mind-muscle connection with your inner thighs. These are great for adding thigh mass from the front! Push them hard!"
               },
               {
                 "name": "A2: Sissy Squat",
                 "equipment": "BODYWEIGHT",
                 "sets": 2,
                 "reps": 10,
                 "rest": 0,
                 "substitutions": [
                   "Leg Extension",
                   "Goblet Squat"
                 ],
                 "notes": "Allow yourself to come up onto your toes and push your knees forward past your toes. This is safe for the knees. If you feel knee pain doing them, though, feel free to go with a substitution. They may feel awkward at first, but they really underrated for the quads! do not give up on them too quickly."
               },
               {
                 "name": "Standing Calf Raise",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Leg Press Calf Press",
                   "Donkey Calf Raise"
                 ],
                 "notes": "1-2 second pause at the bottom of each rep. Instead of just going up onto your toes, think about rolling your ankle back and forth on the balls of your feet."
               }
             ]
           },
           {
             "name": "Arms & Weak Points",
             "exercises": [
               {
                 "name": "Incline Cable Lateral Raise",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 60,
                 "notes": "Decide on your weak point using The Weak Point Table in your Hypertrophy Handbook. Perform ONE of the exercises listed under Exercise 1 for the sets and reps provided here.",
                 "substitutions": [
                   "DB Lateral Raise",
                   "Machine Lateral Raise"
                 ]
               },
               {
                 "name": "Bayesian Cable Curl",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "DB Incline Curl",
                   "DB Scott Curl"
                 ],
                 "notes": "If you have a left-right bicep size imbalance, do these 1 arm at a time, starting with the weaker arm. Take the weaker arm to an RPE of 9-10. Then match the reps with the other arm (stop once you havee matched the reps, even if the RPE is lower). If you do not have a size imbalance, do these both arms at the same time."
               },
               {
                 "name": "Seated DB French Press",
                 "equipment": "DUMBBELL",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "EZ-bar Skull Crusher",
                   "DB Skull Crusher"
                 ],
                 "notes": "Place both palms under the head of a dumbbell and perform overhead extensions. Feel a deep stretch on your triceps at the bottom. Avoid pausing at the top of each rep."
               },
               {
                 "name": "Bottom-2/3 Constant Tension Preacher Curl",
                 "equipment": "OTHER",
                 "sets": 2,
                 "reps": 12,
                 "rest": 90,
                 "substitutions": [
                   "Bottom-2/3 EZ-Bar Curl",
                   "Spider Curl"
                 ],
                 "notes": "Stay in the bottom 2/3 of the curl. do not squeeze all the way up to the top. Keep your triceps firmly pinned against the pad as you curl. No pausing at the top or bottom: constant tension on the biceps!"
               },
               {
                 "name": "Cable Triceps Kickback",
                 "equipment": "CABLE",
                 "sets": 2,
                 "reps": 12,
                 "rest": 90,
                 "substitutions": [
                   "Bench Dip",
                   "DB Triceps Kickback"
                 ],
                 "notes": "There are two ways you can do this: upright or bent over. Choose the one that feels more comfortable for you. The main thing is that when you are in the full squeeze, your shoulder should be positioned back behind your torso."
               },
               {
                 "name": "Cable Crunch",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Machine Crunch",
                   "Plate-Weighted Crunch"
                 ],
                 "notes": "Round your lower back as you crunch. Maintain a mind-muscle connection with your 6-pack."
               }
             ]
           }
         ]
       },
       {
         "week": 6,
         "days": [
           {
             "name": "Upper #1",
             "exercises": [
               {
                 "name": "Cuffed Behind-The-Back Lateral Raise",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Cross-Body Cable Y-Raise",
                   "DB Lateral Raise"
                 ],
                 "notes": "Raise the cables up in a \"Y\" motion. Really try to connect with the middle delt fibers as you sweep the weight up and out."
               },
               {
                 "name": "Lat-Focused Cable Row",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Half-Kneeling 1-Arm Lat Pulldown",
                   "Elbows-In 1-Arm DB Row"
                 ],
                 "notes": "Keep your torso locked in a fixed position (do not lean forward on the negative). Drive your elbows down and back to engage the lats. Keep your elbows tucked in to your sides."
               },
               {
                 "name": "Low Incline DB Press",
                 "equipment": "DUMBBELL",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Low Incline Machine Press",
                   "Low Incline Barbell Press"
                 ],
                 "notes": "Set the bench at a ~15\u00b0 incline. Do a slight elbow tuck on the negative and then flare as you push (assuming this does not bother your shoulders). Nice, smooth reps here. No pausing at the top or bottom: constant tension on the pecs!"
               },
               {
                 "name": "Chest-Supported T-Bar Row + Kelso Shrug",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 8,
                 "rest": 120,
                 "substitutions": [
                   "Machine Chest-Supported Row + Kelso Shrug",
                   "Incline Chest-Supported DB Row + Kelso Shrug"
                 ],
                 "notes": "Do 8-10 reps as a normal T-Bar row, driving your elbows back at roughly 45\u00b0 and squeezing your shoulder blades together. Without resting, do another 4-6 reps as Kelso Shrugs (just squeeze your shoulder blades together without rowing all the way back with your arms)."
               },
               {
                 "name": "Bent-Over Cable Pec Flye (w/ Integrated Partials)",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 12,
                 "rest": 90,
                 "substitutions": [
                   "Pec Deck (w/ Integrated Partials)",
                   "DB Flye (w/ Integrated Partials)"
                 ],
                 "notes": "Lean forward until your torso is parallel with the floor, flye straight out and down toward the floor. Stretch and squeeze the pecs! Stay locked in."
               },
               {
                 "name": "1-Arm Lat Pull-In",
                 "equipment": "OTHER",
                 "sets": 2,
                 "reps": 12,
                 "rest": 90,
                 "substitutions": [
                   "Wide-Grip Lat Pulldown",
                   "Wide-Grip Band-Assisted Pull-Up"
                 ],
                 "notes": "Pull the cable in from the side. Keep a mind-muscle connection with your lats and try to prevent your biceps from taking over. Palpate (feel) your lats with your other hand if that helps you connect with them better."
               },
               {
                 "name": "Dual-Cable Triceps Press",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Overhead Cable Triceps Extension (Bar)",
                   "DB Skull Crusher"
                 ],
                 "notes": "Hold the cables without a handle and get them into position just above your shoulders (around chin level). Press the weight forward (straight out in front of you), not up overhead like in a standard overhead triceps extension."
               }
             ]
           },
           {
             "name": "Lower #1",
             "exercises": [
               {
                 "name": "Seated Leg Curl",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Lying Leg Curl",
                   "Nordic Ham Curl"
                 ],
                 "notes": "Lean forward over the machine to get a maximum stretch in your hamstrings."
               },
               {
                 "name": "Machine Hip Adduction",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Cable Hip Adduction",
                   "Copenhagen Hip Adduction"
                 ],
                 "notes": "Mind-muscle connection with your inner thighs. These are great for adding thigh mass from the front! Push them hard!"
               },
               {
                 "name": "Smith Machine Squat",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 6,
                 "rest": 180,
                 "substitutions": [
                   "Machine Squat",
                   "DB Bulgarian Split Squat"
                 ],
                 "notes": "We are using a reverse pyramid on this exercise. Warm-up as usual to your first working set for 4 reps. This first set will be your heaviest set. Then for set 2, drop the weight back ~10-15% and do 6 reps. Then for set 3, drop the weight back another 10-15% and do 8 reps."
               },
               {
                 "name": "Leg Extension",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "DB Step-Up",
                   "Reverse Nordic"
                 ],
                 "notes": "Set the seat back as far as it will go while still feeling comfortable. Grab the handles as hard as you can to pull your butt down into the seat. Use a 2-3 second negative. Feel your quads pulling apart on the negative."
               },
               {
                 "name": "DB Calf Jumps",
                 "equipment": "DUMBBELL",
                 "sets": 3,
                 "reps": 12,
                 "rest": 90,
                 "substitutions": [
                   "Leg Press Calf Jumps",
                   "Standing Calf Raise"
                 ],
                 "notes": "Hold a dumbbell and perform a jumping motion without actually leaving the floor, using a slight knee bend, but mostly relying on your calves/ankles to drive the \"jump\". I believe I built a lot of calf mass by doing jump rope; these are meant to provide a similar stimulus, but with more tension."
               }
             ]
           },
           {
             "name": "Upper #2",
             "exercises": [
               {
                 "name": "Dual-Handle Lat Pulldown (Mid-back + Lats)",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Overhand Lat Pulldown",
                   "Pull-Up"
                 ],
                 "notes": "Lean back by ~15\u00b0 and drive your elbows down as you squeeze your shoulder blades together. This should feel like a mix of lats and mid-traps."
               },
               {
                 "name": "Seated DB Shoulder Press",
                 "equipment": "DUMBBELL",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Seated Barbell Shoulder Press",
                   "Standing DB Arnold Press"
                 ],
                 "notes": "Slightly rotate the dumbbells in on the negative and flare your elbows out as you push."
               },
               {
                 "name": "Chest-Supported Machine Row",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Chest-Supported T-Bar Row",
                   "Helms Row"
                 ],
                 "notes": "Flare elbows out at roughly 45\u00b0 and squeeze your shoulder blades together hard at the top of each rep."
               },
               {
                 "name": "Decline Machine Chest Press",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Decline Smith Machine Press",
                   "Decline Barbell Press"
                 ],
                 "notes": "Feel your pecs stretching apart on the negative. Mind-muscle connection with lower pecs."
               },
               {
                 "name": "Concentration Cable Curl",
                 "equipment": "CABLE",
                 "sets": 2,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "DB Concentration Curl",
                   "DB Preacher Curl"
                 ],
                 "notes": "Place your working elbow against your knee and perform strict form curls."
               },
               {
                 "name": "Cross-Body Cable Y-Raise",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Machine Lateral Raise",
                   "DB Lateral Raise"
                 ],
                 "notes": "Think about \"drawing a sword\" as you do the positive. Sweep your arm up, out and across your body. It may take a few weeks to get used to these if you have not done them before, but once they click, they really click."
               },
               {
                 "name": "Rear Delt 45\u00b0 Cable Flye",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 12,
                 "rest": 90,
                 "substitutions": [
                   "DB Rear Delt Swing",
                   "Bent-Over Reverse DB Flye"
                 ],
                 "notes": "Pull with one arm at a time, bracing with your non-working hand against the machine. Try to align your arm and the cable in a straight line at the bottom of the flye."
               }
             ]
           },
           {
             "name": "Lower #2",
             "exercises": [
               {
                 "name": "Lying Leg Curl",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Seated Leg Curl",
                   "Nordic Ham Curl"
                 ],
                 "notes": "Set the machine so that you get the biggest stretch possible at the bottom. Prevent your butt from popping up as you curl. Once you get to the full squeeze, continue with partial reps on the last set."
               },
               {
                 "name": "Smith Machine Reverse Lunge",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 8,
                 "rest": 90,
                 "substitutions": [
                   "DB Reverse Lunge",
                   "DB Walking Lunge"
                 ],
                 "notes": "Set one leg back on the negative and then drive the weight up using your front leg. Try to minimize assistance from your back leg."
               },
               {
                 "name": "Glute-Ham Raise",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 8,
                 "rest": 180,
                 "substitutions": [
                   "Nordic Ham Curl",
                   "Seated Leg Curl"
                 ],
                 "notes": "Cut out the top ~25% of the ROM, where there will be minimal tension on the hamstrings. Control the negative and squeeze your hamstrings to pull yourself up!"
               },
               {
                 "name": "A1: Machine Hip Adduction",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 10,
                 "rest": null,
                 "substitutions": [
                   "Cable Hip Adduction",
                   "Copenhagen Hip Adduction"
                 ],
                 "notes": "Mind-muscle connection with your inner thighs. These are great for adding thigh mass from the front! Push them hard!"
               },
               {
                 "name": "A2: Machine Hip Abduction",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 10,
                 "rest": null,
                 "substitutions": [
                   "Cable Hip Abduction",
                   "Lateral Band Walk"
                 ],
                 "notes": "If possible, use pads to increase the range of motion on the machine. Lean forward and grab onto the machine rails to stretch the glutes further."
               },
               {
                 "name": "Standing Calf Raise",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Seated Calf Raise",
                   "Leg Press Calf Press"
                 ],
                 "notes": "1-2 second pause at the bottom of each rep. Instead of just going up onto your toes, think about rolling your ankle back and forth on the balls of your feet."
               }
             ]
           },
           {
             "name": "Arms & Weak Points",
             "exercises": [
               {
                 "name": "Incline Cable Lateral Raise",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 60,
                 "notes": "Decide on your weak point using The Weak Point Table in your Hypertrophy Handbook. Perform ONE of the exercises listed under Exercise 1 for the sets and reps provided here.",
                 "substitutions": [
                   "DB Lateral Raise",
                   "Machine Lateral Raise"
                 ]
               },
               {
                 "name": "Slow-Eccentric EZ-Bar Skull Crusher",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Slow-Eccentric DB Skull Crusher",
                   "Slow-Eccentric DB French Press"
                 ],
                 "notes": "Use a 3-4 second negative. Arc the EZ-bar slightly back behind your head. When you extend, keep the bar back behind your eye line. Use the inside (closer) grip option and allow the elbows to flare to a degree that feels comfortable."
               },
               {
                 "name": "Slow-Eccentric Bayesian Curl",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Slow-Eccentric DB Incline Curl",
                   "Slow-Eccentric DB Scott Curl"
                 ],
                 "notes": "Use a 3-4 second negative and a slight pause at the bottom of each rep to emphasize stretching your biceps."
               },
               {
                 "name": "Triceps Diverging Pressdown (Long Rope or 2 Ropes)",
                 "equipment": "OTHER",
                 "sets": 2,
                 "reps": 12,
                 "rest": 90,
                 "substitutions": [
                   "Cable Triceps Kickback",
                   "DB Triceps Kickback"
                 ],
                 "notes": "Use two long ropes or one long rope. Lean slightly forward, flare your elbows slightly out and keep your arms back in line with your torso. Then do triceps pressdowns, getting a full, big squeeze at the bottom."
               },
               {
                 "name": "Reverse-Grip Cable Curl",
                 "equipment": "CABLE",
                 "sets": 2,
                 "reps": 12,
                 "rest": 90,
                 "substitutions": [
                   "Reverse-Grip EZ-Bar Curl",
                   "Reverse-Grip DB Curl"
                 ],
                 "notes": "Grab a cable bar with your palms facing down and perform curls. These will work the back of your forearm, brachialis and biceps!"
               },
               {
                 "name": "Roman Chair Leg Raise",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 15,
                 "rest": 90,
                 "substitutions": [
                   "Hanging Leg Raise",
                   "Reverse Crunch"
                 ],
                 "notes": "Allow your lower back to round as you curl your legs up. 10-20 reps is a broad range on purpose: just go until you hit RPE 9-10 (0-1 reps shy of failure) with controlled form."
               }
             ]
           }
         ]
       },
       {
         "week": 7,
         "days": [
           {
             "name": "Upper #1",
             "exercises": [
               {
                 "name": "Cuffed Behind-The-Back Lateral Raise",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Cross-Body Cable Y-Raise",
                   "DB Lateral Raise"
                 ],
                 "notes": "Raise the cables up in a \"Y\" motion. Really try to connect with the middle delt fibers as you sweep the weight up and out."
               },
               {
                 "name": "Lat-Focused Cable Row",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Half-Kneeling 1-Arm Lat Pulldown",
                   "Elbows-In 1-Arm DB Row"
                 ],
                 "notes": "Keep your torso locked in a fixed position (do not lean forward on the negative). Drive your elbows down and back to engage the lats. Keep your elbows tucked in to your sides."
               },
               {
                 "name": "Low Incline DB Press",
                 "equipment": "DUMBBELL",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Low Incline Machine Press",
                   "Low Incline Barbell Press"
                 ],
                 "notes": "Set the bench at a ~15\u00b0 incline. Do a slight elbow tuck on the negative and then flare as you push (assuming this does not bother your shoulders). Nice, smooth reps here. No pausing at the top or bottom: constant tension on the pecs!"
               },
               {
                 "name": "Chest-Supported T-Bar Row + Kelso Shrug",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 8,
                 "rest": 120,
                 "substitutions": [
                   "Machine Chest-Supported Row + Kelso Shrug",
                   "Incline Chest-Supported DB Row + Kelso Shrug"
                 ],
                 "notes": "Do 8-10 reps as a normal T-Bar row, driving your elbows back at roughly 45\u00b0 and squeezing your shoulder blades together. Without resting, do another 4-6 reps as Kelso Shrugs (just squeeze your shoulder blades together without rowing all the way back with your arms)."
               },
               {
                 "name": "Bent-Over Cable Pec Flye (w/ Integrated Partials)",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 12,
                 "rest": 90,
                 "substitutions": [
                   "Pec Deck (w/ Integrated Partials)",
                   "DB Flye (w/ Integrated Partials)"
                 ],
                 "notes": "Lean forward until your torso is parallel with the floor, flye straight out and down toward the floor. Stretch and squeeze the pecs! Stay locked in."
               },
               {
                 "name": "1-Arm Lat Pull-In",
                 "equipment": "OTHER",
                 "sets": 2,
                 "reps": 12,
                 "rest": 90,
                 "substitutions": [
                   "Wide-Grip Lat Pulldown",
                   "Wide-Grip Band-Assisted Pull-Up"
                 ],
                 "notes": "Pull the cable in from the side. Keep a mind-muscle connection with your lats and try to prevent your biceps from taking over. Palpate (feel) your lats with your other hand if that helps you connect with them better."
               },
               {
                 "name": "Dual-Cable Triceps Press",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Overhead Cable Triceps Extension (Bar)",
                   "DB Skull Crusher"
                 ],
                 "notes": "Hold the cables without a handle and get them into position just above your shoulders (around chin level). Press the weight forward (straight out in front of you), not up overhead like in a standard overhead triceps extension."
               }
             ]
           },
           {
             "name": "Lower #1",
             "exercises": [
               {
                 "name": "Seated Leg Curl",
                 "equipment": "OTHER",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Lying Leg Curl",
                   "Nordic Ham Curl"
                 ],
                 "notes": "Lean forward over the machine to get a maximum stretch in your hamstrings."
               },
               {
                 "name": "Machine Hip Adduction",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Cable Hip Adduction",
                   "Copenhagen Hip Adduction"
                 ],
                 "notes": "Mind-muscle connection with your inner thighs. These are great for adding thigh mass from the front! Push them hard!"
               },
               {
                 "name": "Smith Machine Squat",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 6,
                 "rest": 180,
                 "substitutions": [
                   "Machine Squat",
                   "DB Bulgarian Split Squat"
                 ],
                 "notes": "We are using a reverse pyramid on this exercise. Warm-up as usual to your first working set for 4 reps. This first set will be your heaviest set. Then for set 2, drop the weight back ~10-15% and do 6 reps. Then for set 3, drop the weight back another 10-15% and do 8 reps."
               },
               {
                 "name": "Leg Extension",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "DB Step-Up",
                   "Reverse Nordic"
                 ],
                 "notes": "Set the seat back as far as it will go while still feeling comfortable. Grab the handles as hard as you can to pull your butt down into the seat. Use a 2-3 second negative. Feel your quads pulling apart on the negative."
               },
               {
                 "name": "DB Calf Jumps",
                 "equipment": "DUMBBELL",
                 "sets": 3,
                 "reps": 12,
                 "rest": 90,
                 "substitutions": [
                   "Leg Press Calf Jumps",
                   "Standing Calf Raise"
                 ],
                 "notes": "Hold a dumbbell and perform a jumping motion without actually leaving the floor, using a slight knee bend, but mostly relying on your calves/ankles to drive the \"jump\". I believe I built a lot of calf mass by doing jump rope; these are meant to provide a similar stimulus, but with more tension."
               }
             ]
           },
           {
             "name": "Upper #2",
             "exercises": [
               {
                 "name": "Dual-Handle Lat Pulldown (Mid-back + Lats)",
                 "equipment": "CABLE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Overhand Lat Pulldown",
                   "Pull-Up"
                 ],
                 "notes": "Lean back by ~15\u00b0 and drive your elbows down as you squeeze your shoulder blades together. This should feel like a mix of lats and mid-traps."
               },
               {
                 "name": "Seated DB Shoulder Press",
                 "equipment": "DUMBBELL",
                 "sets": 3,
                 "reps": 10,
                 "rest": 90,
                 "substitutions": [
                   "Seated Barbell Shoulder Press",
                   "Standing DB Arnold Press"
                 ],
                 "notes": "Slightly rotate the dumbbells in on the negative and flare your elbows out as you push."
               },
               {
                 "name": "Chest-Supported Machine Row",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Chest-Supported T-Bar Row",
                   "Helms Row"
                 ],
                 "notes": "Flare elbows out at roughly 45\u00b0 and squeeze your shoulder blades together hard at the top of each rep."
               },
               {
                 "name": "Decline Machine Chest Press",
                 "equipment": "MACHINE",
                 "sets": 3,
                 "reps": 10,
                 "rest": 120,
                 "substitutions": [
                   "Decline Smith Machine Press",
                   "Decline Barbell Press"
                 ],
                 "notes": "Feel your pecs stretching apart on the negative. Mind-muscle connection with lower pecs."
               }
             ]
           }
         ]
       }
     ]'::jsonb,
    'http://localhost:9000/terra-ai-bucket/ayo/programs/program2.webp',
    ARRAY['hypertrophy', 'upper/lower', '5days', 'male', 'female']::text[],
    true
);

INSERT INTO exercise (name, target_muscle, equipment, video_url, image_url) VALUES
('Incline Cable Lateral Raise', 'SHOULDERS', 'CABLE', 'https://example.com', 'https://example.com'),
('Incline Chest-Supported DB Row + Kelso Shrug', 'BACK', 'OTHER', 'https://example.com', 'https://example.com'),
('Inverse DB Zottman Curl', 'BICEPS', 'DUMBBELL', 'https://example.com', 'https://example.com'),
('Lat Pulldown', 'BACK', 'OTHER', 'https://example.com', 'https://example.com'),
('Lat-Focused Cable Row', 'BACK', 'CABLE', 'https://example.com', 'https://example.com'),
('Lateral Band Walk', 'GLUTES', 'OTHER', 'https://example.com', 'https://example.com'),
('Leg Extension', 'LEGS', 'MACHINE', 'https://example.com', 'https://example.com'),
('Leg Press', 'LEGS', 'MACHINE', 'https://example.com', 'https://example.com'),
('Leg Press Calf Jumps', 'CALVES', 'OTHER', 'https://example.com', 'https://example.com'),
('Leg Press Calf Press', 'CALVES', 'MACHINE', 'https://example.com', 'https://example.com'),
('Low Incline Barbell Press', 'CHEST', 'OTHER', 'https://example.com', 'https://example.com'),
('Low Incline DB Press', 'CHEST', 'DUMBBELL', 'https://example.com', 'https://example.com'),
('Low Incline Machine Press', 'CHEST', 'MACHINE', 'https://example.com', 'https://example.com'),
('Low Incline Smith Machine Press', 'CHEST', 'MACHINE', 'https://example.com', 'https://example.com'),
('Lying Leg Curl', 'LEGS', 'MACHINE', 'https://example.com', 'https://example.com'),
('Machine Chest-Supported Row + Kelso Shrug', 'BACK', 'MACHINE', 'https://example.com', 'https://example.com'),
('Machine Crunch', 'CORE', 'MACHINE', 'https://example.com', 'https://example.com'),
('Machine Hip Adduction', 'GLUTES', 'MACHINE', 'https://example.com', 'https://example.com'),
('Machine Lat Pullover', 'BACK', 'MACHINE', 'https://example.com', 'https://example.com'),
('Machine Lateral Raise', 'SHOULDERS', 'MACHINE', 'https://example.com', 'https://example.com'),
('Machine Pulldown', 'BACK', 'MACHINE', 'https://example.com', 'https://example.com'),
('Machine Shoulder Press', 'SHOULDERS', 'MACHINE', 'https://example.com', 'https://example.com'),
('Machine Squat', 'LEGS', 'MACHINE', 'https://example.com', 'https://example.com'),
('Neutral-Grip Pullup', 'BACK', 'MACHINE', 'https://example.com', 'https://example.com'),
('Nordic Ham Curl', 'LEGS', 'DUMBBELL', 'https://example.com', 'https://example.com'),
('Overhand Lat Pulldown', 'BACK', 'OTHER', 'https://example.com', 'https://example.com'),
('Overhand Machine Row', 'BACK', 'MACHINE', 'https://example.com', 'https://example.com'),
('Overhead Cable Triceps Extension (Bar)', 'TRICEPS', 'CABLE', 'https://example.com', 'https://example.com'),
('Overhead Cable Triceps Extension (Rope)', 'TRICEPS', 'OTHER', 'https://example.com', 'https://example.com'),
('Paused Assisted Dip', 'CHEST', 'BODYWEIGHT', 'https://example.com', 'https://example.com'),
('Paused Barbell RDL', 'LEGS', 'BARBELL', 'https://example.com', 'https://example.com'),
('Paused DB RDL', 'LEGS', 'OTHER', 'https://example.com', 'https://example.com'),
('Pec Deck (w/ Integrated Partials)', 'CHEST', 'MACHINE', 'https://example.com', 'https://example.com'),
('Plate-Weighted Crunch', 'CORE', 'OTHER', 'https://example.com', 'https://example.com'),
('Pull-Up', 'BACK', 'OTHER', 'https://example.com', 'https://example.com'),
('Rear Delt 45° Cable Flye', 'SHOULDERS', 'CABLE', 'https://example.com', 'https://example.com'),
('Reverse Crunch', 'CORE', 'OTHER', 'https://example.com', 'https://example.com'),
('Reverse Nordic', 'LEGS', 'OTHER', 'https://example.com', 'https://example.com'),
('Reverse Pec Deck', 'SHOULDERS', 'OTHER', 'https://example.com', 'https://example.com'),
('Reverse-Grip Cable Curl', 'BICEPS', 'CABLE', 'https://example.com', 'https://example.com'),
('Reverse-Grip DB Curl', 'BICEPS', 'OTHER', 'https://example.com', 'https://example.com'),
('Reverse-Grip EZ-Bar Curl', 'BICEPS', 'OTHER', 'https://example.com', 'https://example.com'),
('Roman Chair Leg Raise', 'CORE', 'OTHER', 'https://example.com', 'https://example.com'),
('Seated Barbell Shoulder Press', 'SHOULDERS', 'OTHER', 'https://example.com', 'https://example.com'),
('Seated Calf Raise', 'CALVES', 'OTHER', 'https://example.com', 'https://example.com'),
('Seated DB French Press', 'TRICEPS', 'DUMBBELL', 'https://example.com', 'https://example.com'),
('Seated DB Shoulder Press', 'SHOULDERS', 'DUMBBELL', 'https://example.com', 'https://example.com'),
('Seated Leg Curl', 'LEGS', 'OTHER', 'https://example.com', 'https://example.com'),
('Slow-Eccentric Bayesian Curl', 'BICEPS', 'OTHER', 'https://example.com', 'https://example.com'),
('Slow-Eccentric DB Curl', 'BICEPS', 'OTHER', 'https://example.com', 'https://example.com'),
('Slow-Eccentric DB French Press', 'TRICEPS', 'OTHER', 'https://example.com', 'https://example.com');