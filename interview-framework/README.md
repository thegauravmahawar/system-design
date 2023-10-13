# A Framework for System Design Interviews

The system design interview simulates real-life problem solving where two co-workers collaborate on an ambiguous problem and come up with a solution that meets their goals. The problem is open-ended, and there is no perfect answer.

An effective system design interview gives strong signals about a person's ability to collaborate, to work under pressure, and to resolve ambiguity constructively. The ability to ask good questions is also an essential skill, and many interviewers specifically look for this skill.

Avoid over-engineering. Write down trade-offs.

## A 4-step process for effective System Design interview

### Step 1 - Understand the problem and establish design scope

In a system design interview, giving out an answer quickly without thinking gives you no bonus points. Answering without a thorough understanding of the requirements is a huge red flag as the interview is not a trivia contest. 

**Think deeply and ask questions to clarify the requirements and assumptions.**

Write down your assumptions on the whiteboard or paper. You might need them later.

A list of questions to get started:

- What specific features are we going to build?
- How many users does the product have?
- How fast does the company anticipate to scale up?
- What existing services you might leverage to simplify the design?

Sample questions to ask an interviewer for designing a News Feed:

- Is this a mobile app? Or a web app? Or both?
- Is the news feed sorted in reverse chronological order or a custom order? Custom order means each post is given a different weight. For instance, posts from your close friends are more important than posts from a group.
- What is the traffic volume?
- Can the feed contain images, videos, or just text?

### Step 2 - Propose high-level design and get buy-in
