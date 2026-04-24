# Week 7 GitHub And CI Guide

This guide supports the Week 7 applied-session workflow.

## Goal

You will:

1. create a new GitHub repository in the browser,
2. upload the `mockito-tut-starter` project,
3. add a GitHub Actions workflow,
4. observe an initial failing CI run,
5. complete Phase 1 and recover a green run,
6. complete Phase 2 and recover a green run again.

## Create A GitHub Repository

1. Sign in to GitHub.
2. Create a new repository.
3. Choose a simple repository name such as `fit5171-week7`.
4. Create the repository without adding a README or `.gitignore`.
5. Open the repository page in your browser.

Official GitHub guide:

- [Uploading a project to GitHub](https://docs.github.com/en/get-started/start-your-journey/uploading-a-project-to-github)

## Upload The Starter Project

1. Open the `mockito-tut-starter` folder on your computer.
2. Drag or upload the folder contents into the empty GitHub repository.
3. Commit the uploaded files in the browser.

At this point, the repository has no CI workflow yet.

## Add GitHub Actions

1. In your GitHub repository, create a new file at `.github/workflows/ci.yml`.
2. Copy the contents of `week7-ci-template.yml` into that file.
3. Commit the workflow file.
4. Open the `Actions` tab and inspect the first run.

That first run should fail because the starter project still contains unfinished Week 7 work.

Official GitHub guide:

- [Quickstart for GitHub Actions](https://docs.github.com/en/actions/writing-workflows/quickstart)

## Week 7 Workflow

### Phase 1

Implement the validation and error behaviour in `UserHandler.updatePassword(...)` so that:

- a missing user causes `SQLException` with a helpful message,
- a `null` password returns `false`,
- the same password returns `false`.

For Phase 1, keep the Phase 2 success-path template disabled.

Expected result:

- `mvn test` passes locally,
- GitHub Actions passes after you push the Phase 1 changes.

### Phase 2

Then complete the success path:

- uncomment and complete the `differentPasswordReturnsTrue()` test template,
- implement the happy path in `UserHandler`,
- return `true`,
- update the `User`,
- call `userDAO.update(user)`.

Expected result:

- `mvn test` passes locally again,
- GitHub Actions passes again after you push the Phase 2 changes.

## Notes

- The main CI workflow should run `mvn test`.
- Do not use `-Pextra-practice` in the required Week 7 CI workflow.
- The extra-practice profile is only for optional extension tasks.
