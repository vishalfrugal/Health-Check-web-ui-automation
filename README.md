# Web UI Automation — Health Check

Project: Web UI Automation Health Check

This repository contains TestNG + Selenium-based UI automation health-check tests for multiple products (KnowledgeGym, AI features, Admin/User flows). Tests use Maven for build and dependency management and integrate with Allure for test reporting and screenshot attachments.

Important files and folders

- `pom.xml` — Maven project file (dependencies, plugins).
- `testng.xml` — TestNG suite configuration; registers `EventListeners.AllureScreenShot` listener.
- `src/main/java` and `src/test/java` — main and test code.
- `Utility` (e.g. `Utility/utils.java`) — helper utilities such as screenshot capture and soft-assert handling.
- `allure-results/` — Allure result files and attachments produced by test runs.

Prerequisites

- JDK 11+ (compatible with project setup)
- Maven 3.6+
- Chrome (or Chromium-based browser) matching your WebDriver
- ChromeDriver (or appropriate driver) on PATH or configured in your code
- (Optional) Allure Commandline installed for serving/generating reports (https://docs.qameta.io/allure/)

Quick start — run all tests

Open PowerShell in project root and run:

```powershell
mvn clean test
```

Run a single test method/class (example):

```powershell
mvn -Dtest=Tests.KnowledgeGymGermanyTest#VerifyKnowledgeGymGermanyHealthCheck test
```

Generating and viewing Allure report

If you have Allure CLI installed, from project root run:

```powershell
# serve results locally (opens browser)
allure serve allure-results

# or generate a static report into 'allure-report' and open it
allure generate allure-results -o allure-report --clean; Start-Process allure-report\index.html
```

If you don't have Allure CLI, you can also configure the Maven Allure plugin in `pom.xml` to create a report as part of the build.

Helpful debugging steps (practical checklist)

1. Reproduce locally with a single failing test and add extra logs.
2. Confirm `driver` is not null in the test setup; add debug log right before screenshot call:
   - "driver is null? -> {true/false}" and "page title = ...".
3. Disable the listener in `testng.xml` temporarily to see whether duplicate screenshots disappear.
4. Add a timestamped file save in screenshot utility so you can inspect images on disk.
5. If screenshot images are blank, run tests non-headless and/or add a 2-3s Thread.sleep (temporary) before capture to check timing.
6. Ensure `softAssert.assertAll()` is invoked at the end of tests that use soft assertions.

Recommended small code changes (safe, non-breaking)

- Add defensive null-checks in `Utility/utils.java` before taking screenshots.
- Centralize screenshot capture in the TestNG listener (remove duplicate manual captures from tests) or vice versa.

Contributing

1. Create an issue describing the bug or enhancement.
2. Create a branch named `feature/<short-description>` or `fix/<short-description>`.
3. Open a pull request with a description and testing instructions.

Contact / Maintainers

If something is unclear or you want help debugging a specific failing test or `utils.java`, share the relevant files:
- `src/test/java/...` test file that fails (e.g., `Tests/KnowledgeGymGermanyTest.java`)
- `Utility/utils.java` (screenshot utility)
- `EventListeners/AllureScreenShot.java` (listener implementation)

With those I can give targeted code fixes (for example, show how to avoid double screenshots and how to handle null `driver` safely).

CI / GitHub Actions (optional)

You can run this test suite on CI using GitHub Actions. A typical workflow:

- Trigger: on push or on a schedule.
- Job: use an Ubuntu runner, install JDK and Chrome / ChromeDriver, run `mvn clean test`.
- Artifacts: upload `allure-results/` and optionally publish an Allure report or store it as an artifact.

Example (concise) workflow snippet for `.github/workflows/ci.yml`:

```yaml
name: UI Tests
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - name: Set up JDK
        uses: actions/setup-java@v4
        with:
          distribution: 'temurin'
          java-version: '11'
      - name: Install Chrome
        run: sudo apt-get update && sudo apt-get install -y wget unzip xvfb libxi6 libgconf-2-4
      - name: Install ChromeDriver
        run: |
          CHROME_VERSION=$(google-chrome --version | sed -E 's/.* ([0-9]+)\..*/\1/')
          # install matching chromedriver logic here (or use a fixed driver)
      - name: Run tests
        run: mvn -B clean test
      - name: Upload Allure results
        uses: actions/upload-artifact@v4
        with:
          name: allure-results
          path: allure-results/
```

This is intentionally minimal — adapt browser/driver installation to match your environment (or use prebuilt actions that install Chrome/ChromeDriver).

Slack notifications (brief)

To notify a Slack channel on test failures or on workflow completion you can:

- Use Slack Incoming Webhooks: create a webhook URL, then POST a JSON payload from a step in your GitHub Actions workflow when results indicate failure.
- Or use a community GitHub Action such as `8398a7/action-slack` which wraps Slack notification logic and supports rich status messages and attachments.

Concise example (send a simple message using curl in a workflow step):

```yaml
- name: Notify Slack
  if: failure()
  env:
    SLACK_WEBHOOK: ${{ secrets.SLACK_WEBHOOK }}
  run: |
    curl -X POST -H 'Content-type: application/json' --data '{"text":"UI tests failed: $GITHUB_RUN_ID"}' "$SLACK_WEBHOOK"
```

Keep secrets (`SLACK_WEBHOOK`) in GitHub repository secrets. For richer reporting, upload `allure-report` as artifact and share a link or attach summary counts in the message.

---

End of README
