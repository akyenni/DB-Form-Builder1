# Dynamic PostgreSQL Form Designer

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Bootstrap](https://img.shields.io/badge/Bootstrap-5.3.0-purple.svg)](https://getbootstrap.com/)
[![jQuery](https://img.shields.io/badge/jQuery-3.7.0-blue.svg)](https://jquery.com/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-13+-blue.svg)](https://www.postgresql.org/)

A comprehensive, drag-and-drop form designer that creates dynamic forms based on PostgreSQL database tables with advanced validation, dependency management, and database integration.

## 🌟 Features

### Core Functionality
- **🎨 Drag & Drop Interface** - Intuitive form building with database fields
- **📊 Multiple Form Modes** - Single table, tabbed, sectioned, and wizard layouts
- **🔧 Real-time Configuration** - Live property panel with instant updates
- **✅ Advanced Validation** - Required, length, pattern, email, number, and custom validations
- **🔗 Smart Dependencies** - Show/hide/enable/disable fields based on conditions
- **📱 Responsive Design** - Mobile-friendly interface and data entry forms
- **💾 Database Integration** - Save/load forms with PostgreSQL JSON storage
- **🔄 Element Reordering** - Drag handles for easy field repositioning
- **🔄 Multiple Indian Languages** - Support for Multiple Indian Languages such as Hindi, Telugu, Tamil, Kannada, Malayalam, Gujarati apart from default English
- **🔄 Dashboard for Forms and Datra submitted** - Comprehensive Dashboard with analytics

### Form Layout Options
1. **Single Table** - Linear form layout for simple data entry
2. **Tabbed** - Multi-table forms with tab navigation
3. **Sectioned** - Card-based sections (sollapsible) grouped by table
4. **Wizard** - Step-by-step process with progress tracking

## 🚀 Quick Start

### Prerequisites
- Modern web browser (Chrome, Firefox, Safari, Edge)
- PostgreSQL database (version 16+)
- Web server (Apache Tomcat, Nginx, or development server)

### Installation

1. **Clone or download the files:**
   ```bash
   git clone <repository-url>
   cd dynamic-form-designer
   ```

2. **Set up PostgreSQL database:**
   ```sql
   -- Run the database schema (provided in the application)
   -- Creates tables: form_structures, form_fields, form_field_validations, 
   -- form_field_dependencies, form_submissions
   ```

3. **Configure API endpoints:**
   Update the `API_ENDPOINTS` object in the JavaScript:
   ```javascript
   const API_ENDPOINTS = {
       SAVE_FORM: '/api/forms/save',
       LOAD_FORMS: '/api/forms/list',
       LOAD_FORM: '/api/forms/load',
       DELETE_FORM: '/api/forms/delete',
       SUBMIT_DATA: '/api/forms/submit'
   };
   ```

4. **Open the application:**
   - Serve the HTML file through a web server
   - Open `complete_form_designer.html` in your browser

## 📖 Usage Guide

### 1. Form Design Process

#### Step 1: Configure Form Details
```
Form Designer Panel → Enter form name, description, expiry date
```

#### Step 2: Select Form Mode
- **Single Table**: For simple forms using one database table
- **Tabbed**: Multi-table forms with tab-based navigation
- **Sectioned**: Multi-table forms with visual sections (collapsible)
- **Wizard**: Step-by-step multi-table forms

#### Step 3: Add Form Fields
- Drag fields from the database tables panel
- Drop into the design area or specific tabs (tabbed mode)
- Use "Drop All Mandatory Fields" for quick setup

#### Step 4: Configure Field Properties
- Click on any form element to open properties panel
- Set label, placeholder, help text
- Configure required/readonly status
- Add validations and dependencies

#### Step 5: Save to Database
```
Save to Database → Enter version notes → Confirm save
```

### 2. Form Field Configuration

#### Basic Properties
- **Label**: Display name for the field
- **Placeholder**: Hint text for input fields
- **Help Text**: Additional guidance for users
- **Required**: Make field mandatory
- **Read Only**: Prevent user input

#### Validation Rules
- **Required**: Field must have a value
- **Min/Max Length**: Text length constraints
- **Pattern (Regex)**: Custom validation patterns
- **Email**: Valid email format
- **Number**: Numeric values only
- **Min/Max Value**: Numeric range validation
- **Custom JS**: JavaScript validation functions

#### Dependencies
- **Trigger Field**: Which field to monitor
- **Condition**: equals, not_equals, contains, empty, etc.
- **Value**: Comparison value
- **Action**: show, hide, require, enable, disable

### 3. Data Entry Page Generation

#### Generate Standalone Form
```
Create Data Entry Page → Opens new window with functional form
```

#### Form Features
- **Live Validation**: Real-time field validation with error messages
- **Dependency Logic**: Dynamic field behavior based on rules
- **Data Submission**: Structured JSON data saved to database
- **Mobile Responsive**: Works on all device sizes

## 🗄️ Database Schema

### Core Tables

#### form_structures
Stores complete form configurations
```sql
CREATE TABLE form_structures (
    form_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    form_name VARCHAR(255) NOT NULL,
    form_description TEXT,
    form_mode VARCHAR(20) NOT NULL DEFAULT 'single',
    expiry_date DATE,
    is_active BOOLEAN DEFAULT true,
    version_number INTEGER DEFAULT 1,
    form_config JSONB NOT NULL,
    tables_involved TEXT[],
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

#### form_fields
Individual field properties and mappings
```sql
CREATE TABLE form_fields (
    field_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    form_id UUID NOT NULL REFERENCES form_structures(form_id) ON DELETE CASCADE,
    field_name VARCHAR(100) NOT NULL,
    table_name VARCHAR(100) NOT NULL,
    field_order INTEGER NOT NULL,
    field_type VARCHAR(50) NOT NULL,
    is_primary_key BOOLEAN DEFAULT false,
    foreign_key_reference VARCHAR(200),
    field_properties JSONB NOT NULL
);
```

#### form_field_validations
Validation rules per field
```sql
CREATE TABLE form_field_validations (
    validation_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    field_id UUID NOT NULL REFERENCES form_fields(field_id) ON DELETE CASCADE,
    validation_type VARCHAR(50) NOT NULL,
    validation_value TEXT,
    error_message TEXT NOT NULL,
    validation_order INTEGER DEFAULT 1
);
```

#### form_field_dependencies
Field dependency relationships
```sql
CREATE TABLE form_field_dependencies (
    dependency_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    field_id UUID NOT NULL REFERENCES form_fields(field_id) ON DELETE CASCADE,
    depends_on_field VARCHAR(100) NOT NULL,
    condition_type VARCHAR(50) NOT NULL,
    condition_value TEXT,
    action_type VARCHAR(50) NOT NULL
);
```

#### form_submissions
Actual form data entries
```sql
CREATE TABLE form_submissions (
    submission_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    form_id UUID NOT NULL REFERENCES form_structures(form_id),
    submission_data JSONB NOT NULL,
    submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ip_address INET,
    user_agent TEXT
);
```

## 🔌 API Integration

### Required Endpoints

#### Save Form Structure
```javascript
POST /api/forms/save
Content-Type: application/json

{
  "form_structure": {
    "form_name": "Customer Order Form",
    "form_description": "Complete customer management",
    "form_mode": "wizard",
    "form_config": { /* Complete form JSON */ }
  },
  "form_fields": [ /* Field definitions */ ],
  "validations": [ /* Validation rules */ ],
  "dependencies": [ /* Dependency rules */ ]
}
```

#### Load Forms List
```javascript
GET /api/forms/list
Response: [
  {
    "form_id": "uuid",
    "form_name": "Customer Form",
    "version_number": 1,
    "is_active": true
  }
]
```

#### Load Specific Form
```javascript
GET /api/forms/load/{form_id}
Response: {
  "form_structure": { /* Form configuration */ },
  "form_fields": [ /* Field definitions */ ],
  "validations": [ /* Validation rules */ ],
  "dependencies": [ /* Dependency rules */ ]
}
```

#### Submit Form Data
```javascript
POST /api/forms/submit
Content-Type: application/json

{
  "submissionId": "uuid",
  "formId": "form-uuid",
  "submittedAt": "2024-03-15T10:30:00Z",
  "tables": {
    "users": { "first_name": "John", "email": "john@email.com" },
    "orders": { "total_amount": 299.99, "status": "pending" }
  }
}
```

## 📋 Sample Data Structure

### Form Configuration JSON
```json
{
  "formId": "uuid-generated",
  "name": "Customer Order Form",
  "description": "Complete customer and order management",
  "mode": "wizard",
  "expiryDate": "2024-12-31",
  "tables": ["users", "orders", "order_items"],
  "elements": [
    {
      "field": "first_name",
      "table": "users",
      "type": "varchar",
      "label": "First Name",
      "placeholder": "Enter first name",
      "required": true,
      "validations": [
        {
          "type": "required",
          "message": "First name is required"
        },
        {
          "type": "minLength",
          "value": "2",
          "message": "First name must be at least 2 characters"
        }
      ],
      "dependencies": [
        {
          "field": "user_type",
          "condition": "equals",
          "value": "premium",
          "action": "show"
        }
      ]
    }
  ]
}
```

### Submission Data JSON
```json
{
  "submissionId": "uuid-generated",
  "formId": "form-uuid",
  "formName": "Customer Order Form",
  "submittedAt": "2024-03-15T10:30:00Z",
  "tables": {
    "users": {
      "first_name": "John",
      "last_name": "Doe",
      "email": "john@email.com",
      "phone": "+1234567890"
    },
    "orders": {
      "total_amount": 299.99,
      "status": "pending",
      "shipping_address": "123 Main St, City, State"
    },
    "order_items": {
      "product_name": "Laptop Computer",
      "quantity": 1,
      "unit_price": 299.99
    }
  }
}
```

## 🎨 Customization

### Styling
The application uses Bootstrap 5 with custom CSS. Key customization points:

```css
/* Form Designer Theme Colors */
.form-info-panel {
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}

/* Data Entry Form Theme */
.form-container {
    background: white;
    border-radius: 15px;
    box-shadow: 0 10px 30px rgba(0,0,0,0.2);
}

/* Validation Error Styling */
.validation-error {
    border-color: #dc3545 !important;
}
```

### JavaScript Configuration
```javascript
// Customize field type mappings
function getInputType(dbType) {
    const typeMap = {
        'integer': 'number',
        'decimal': 'number',
        'varchar': 'text',
        'text': 'textarea',
        'date': 'date',
        'timestamp': 'datetime-local',
        'boolean': 'checkbox'
    };
    return typeMap[dbType] || 'text';
}

// Customize validation messages
const defaultValidationMessages = {
    required: 'This field is required',
    email: 'Please enter a valid email address',
    minLength: 'Minimum length not met',
    maxLength: 'Maximum length exceeded'
};
```

## 🔧 Troubleshooting

### Common Issues

#### 1. Fields not appearing in design area
- **Cause**: JavaScript errors or missing jQuery UI
- **Solution**: Check browser console for errors, ensure all CDN links are accessible

#### 2. Properties panel not opening
- **Cause**: Event handlers not attached properly
- **Solution**: Ensure jQuery is loaded before the application scripts

#### 3. Validation not working in data entry
- **Cause**: Missing form configuration or validation setup
- **Solution**: Verify form elements have proper validation rules configured

#### 4. Database save failing
- **Cause**: API endpoints not configured or database connection issues
- **Solution**: Check API endpoint configuration and database connectivity

### Debug Mode
Enable debug logging by adding to the browser console:
```javascript
// Enable debug logging
localStorage.setItem('debugMode', 'true');
```

## 🤝 Contributing

### Development Setup
1. Fork the repository
2. Create a feature branch: `git checkout -b feature-name`
3. Make your changes and test thoroughly
4. Commit your changes: `git commit -am 'Add feature'`
5. Push to the branch: `git push origin feature-name`
6. Submit a pull request

### Coding Standards
- Use ES6+ JavaScript features
- Follow Bootstrap utility-first CSS approach
- Maintain responsive design principles
- Include comprehensive error handling
- Add JSDoc comments for functions

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- **Bootstrap** - For the responsive UI framework
- **jQuery & jQuery UI** - For DOM manipulation and drag-and-drop functionality
- **Font Awesome** - For the comprehensive icon set
- **PostgreSQL** - For the robust database foundation

## 📞 Support

For support, questions, or feature requests:
- Create an issue on GitHub
- Check the troubleshooting section above
- Review the API documentation for integration help

## 🗺️ Roadmap

### Upcoming Features
- [ ] **Form Templates** - Pre-built form templates for common use cases
- [ ] **Advanced Analytics** - Submission analytics and reporting dashboard
- [ ] **Multi-language Support** - Internationalization for labels and messages
- [ ] **Form Permissions** - Role-based access control for forms
- [ ] **API Documentation** - Interactive API documentation with examples
- [ ] **Form Versioning UI** - Visual form version comparison and management
- [ ] **Custom CSS Editor** - Built-in CSS editor for advanced styling
- [ ] **Webhook Integration** - Send form submissions to external services

---

**Built with ❤️ for developers who need powerful, database-driven form solutions.**