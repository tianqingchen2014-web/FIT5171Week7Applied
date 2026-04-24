## Mockito Tutorial Starter

This folder is the student practice copy for the Week 7 applied session.

Start with these files:

- `src/main/java/prolist/logics/UserHandler.java`
- `src/test/java/prolist/logics/UserHandlerUnitTest.java`
- `GITHUB_CI_GUIDE.md`
- `week7-ci-template.yml`

## Required Week 7 Workflow

1. Create a new GitHub repository in the browser.
2. Upload the contents of this starter project.
3. Add a GitHub Actions workflow using `week7-ci-template.yml`.
4. Observe the first failing CI run.
5. Complete Phase 1 and recover green CI.
6. Complete Phase 2 and recover green CI again.

## Phase 1

Implement the validation and error behaviour in `UserHandler.updatePassword(...)` so that:

- a missing user causes `SQLException` with a helpful message,
- a `null` password returns `false`,
- the same password returns `false`.

The active tests in `UserHandlerUnitTest` match this Phase 1 scope.

## Phase 2

After Phase 1 passes, uncomment and complete the success-path test template in `UserHandlerUnitTest`.

Then update `UserHandler.updatePassword(...)` so that a different password:

- changes the stored password,
- calls `userDAO.update(user)`,
- returns `true`.

## Notes

- The default `mvn test` run focuses on the main Week 7 `UserHandler` exercise.
- Use `mvn test` in the required GitHub Actions workflow.
- `Validator` and `EntityMiner` are optional extra practice tasks.
- To run the extra-practice tests, use `mvn test -Pextra-practice`.
- Expect `mvn test -Pextra-practice` to fail until you implement the optional `Validator` and `EntityMiner` work.
- The sibling `mockito-tut` folder keeps one acceptable solution/reference copy.

## Stub And Driver Ideas

- For `UserHandler`, compare Mockito with a handwritten `UserDAO` stub that returns fixed users for selected IDs.
- For `Validator` or `EntityMiner`, a JUnit test method can act as a lightweight driver that exercises the code under test with controlled inputs.
